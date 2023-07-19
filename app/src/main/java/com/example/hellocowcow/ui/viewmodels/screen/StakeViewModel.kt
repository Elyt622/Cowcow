package com.example.hellocowcow.ui.viewmodels.screen

import androidx.lifecycle.ViewModel
import com.example.hellocowcow.datas.response.mvxApi.NftResponse
import com.example.hellocowcow.datas.response.mvxApi.RewardRequest
import com.example.hellocowcow.domain.repositories.NftRepository
import com.walletconnect.util.bytesToHex
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@HiltViewModel
class StakeViewModel @Inject constructor(
    private val nftRepository: NftRepository
) : ViewModel() {

    init {
        getAllDataUsers()
    }

    sealed class UiState {
        object NoData : UiState()
        object Loading : UiState()
        data class Success(val data: List<NftResponse>) : UiState()
        data class Error(val error: String) : UiState()
    }

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    @OptIn(ExperimentalEncodingApi::class)
    fun getAllDataUsers() =
        nftRepository.getAllDataUsers(
            RewardRequest(
                "erd1qqqqqqqqqqqqqpgqqgzzsl0re9e3u0t3mhv3jwg6zu63zssd7yqs3uu9jk",
                "getAllDataForUser",
                "0",
                arrayListOf(),
                "erd1xwnpvwxdwt4ptnmwhyygfm5gzafu2fwxchax9evf6myk3cr00xpsq6np2y"
            )
        )
            .subscribeOn(Schedulers.io())
            .map { data ->
                val segmentedHexList = mutableListOf<String>()
                val decoded: String = Base64.decode(data.returnData[0]).bytesToHex()
                for (i in decoded.indices step 4) {
                    val endIndex = kotlin.math.min(i + 4, decoded.length)
                    val segment = decoded.substring(i, endIndex)
                    if (!segment.contains("0000"))
                        if(segment.startsWith("00"))
                            segmentedHexList.add(segment.substring(2))
                        else
                            segmentedHexList.add(segment)
                }
                val destinationArray = segmentedHexList
                    .subList(
                        1,
                        segmentedHexList[0].toInt(16) + 1)
                    .toList()
                destinationArray
            }.map { list ->

                val identifiersStr = StringBuilder()
                identifiersStr.append("[")

                for (element in list) {
                    if (element != list.last())
                        identifiersStr.append("COW-cd463d-$element,")
                    else
                        identifiersStr.append("COW-cd463d-$element]")
                }
                Timber.tag("DEBUG").d(identifiersStr.toString())
                nftRepository.getCowsWithCollection(identifiersStr.toString(), list.size, 0).toObservable()
            }.subscribeOn(Schedulers.io())
            .subscribeBy {
                it.subscribeBy (
                    onNext = { nfts ->
                        if (nfts.isEmpty())
                            _uiState.value = UiState.NoData
                        else
                            _uiState.value = UiState.Success(nfts)
                    },
                    onError = { error ->
                        _uiState.value = UiState.Error(error.message.toString())
                    }
                ).isDisposed
            }

     private fun checkImageExists(
         imageUrl: String
     ): Single<Boolean> =
         Single.just(try {
            val url = URL(imageUrl)
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "HEAD"
            connection.connect()
            val responseCode = connection.responseCode
            responseCode == HttpURLConnection.HTTP_OK
        } catch (e: Exception) {
            false
        })

    fun loadImages(
        nft: NftResponse
    ) : Single<Array<String>> =
        checkImageExists("https://xoxno.com/api/getCow?identifier=${nft.identifier}")
            .map { imageExist ->
                if (imageExist)
                    arrayOf(
                        nft.url.toString(),
                        "https://xoxno.com/api/getCow?identifier=${nft.identifier}"
                    )
                else
                    arrayOf(nft.url.toString())
            }


}
