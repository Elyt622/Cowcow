package com.example.hellocowcow.ui.viewmodels.screen

import androidx.lifecycle.ViewModel
import com.example.hellocowcow.data.response.mvxApi.NftResponse
import com.example.hellocowcow.domain.repositories.NftRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    private val nftRepository: NftRepository
) : ViewModel() {

    sealed class UiState {
        object NoData : UiState()
        object Loading : UiState()
        data class Success(val data: List<NftResponse>) : UiState()
        data class Error(val error: String) : UiState()
    }

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    fun getAllCowsInWallet(
        address: String
    ) = nftRepository.getAllCowsInWallet(address)
        .subscribeBy (
            onSuccess = { nfts ->
                if (nfts.isNotEmpty())
                    _uiState.value = UiState.Success(nfts)
                else
                    _uiState.value = UiState.NoData
            },
            onError = { error ->
                _uiState.value = UiState.Error(error.message.toString())
            }
        )

}
