package com.example.hellocowcow.ui.viewmodels.screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.hellocowcow.data.response.xoxnoApi.NftResponse
import com.example.hellocowcow.domain.repositories.NftRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MarketViewModel @Inject constructor(
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

    fun getCowsListing() =
        nftRepository.getCowsListing(address.value)
        .map {
            if (it.groupedByCollection.isEmpty())
                _uiState.value = UiState.NoData
            it.groupedByCollection
        }
        .flatMapIterable { it }
        .filter { collection -> collection.ticker == "COW-cd463d" }
        .subscribeBy (
                onNext = {
                    Timber.tag("DEBUGGG").d(it.ticker)
                    if (it.nfts.isNotEmpty())
                        _uiState.value = UiState.Success(it.nfts.toList())
                },
                onError = {
                    _uiState.value = UiState.Error(it.message.toString())
                }
            ).isDisposed

}
