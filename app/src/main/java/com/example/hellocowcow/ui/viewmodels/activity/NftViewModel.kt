package com.example.hellocowcow.ui.viewmodels.activity

import androidx.lifecycle.ViewModel
import com.example.hellocowcow.domain.models.DomainNft
import com.example.hellocowcow.domain.repositories.NftRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class NftViewModel @Inject constructor(
    private val nftRepository: NftRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    sealed class UiState {
        data object Loading : UiState()
        class Success(val nft: DomainNft) : UiState()
        class Error(val error: String) : UiState()
    }

    fun getNft(
        identifier: String
    ) = nftRepository.getNftXoxno(identifier)
            .subscribeBy (
                onSuccess = { nft ->
                    _uiState.value = UiState.Success(nft)
                },
                onError = { error ->
                    _uiState.value = UiState.Error(error.message.toString())
                }
            )


}