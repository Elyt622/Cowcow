package com.example.hellocowcow.ui.viewmodels.screen.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.hellocowcow.data.response.mvxApi.NftResponse
import com.example.hellocowcow.data.response.mvxApi.RewardRequest
import com.example.hellocowcow.domain.repositories.NftRepository
import com.walletconnect.util.bytesToHex
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@HiltViewModel
class StakeViewModel @Inject constructor(
    private val nftRepository: NftRepository
) : ViewModel() {

    private val _address = mutableStateOf("")
    val address: State<String> get() = _address

    fun setAddress(value: String) {
        _address.value = value
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
                address.value
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


}
