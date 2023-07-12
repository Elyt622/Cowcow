package com.example.hellocowcow.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.hellocowcow.domain.models.DomainToken
import com.example.hellocowcow.domain.repositories.TokenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TokenViewModel @Inject constructor(
    private val tokenApi: TokenRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    sealed class UiState {
        object Loading : UiState()
        data class Success(val data: DomainToken) : UiState()
        data class Error(val error: String) : UiState()
    }

    init {
        test()
    }

    private fun test() {
        tokenApi.getToken("MOOVE-875539")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy (
                onSuccess = { token ->
                    _uiState.value = UiState.Success(token)
                },
                onError = { error ->
                    _uiState.value = UiState.Error(error.message.toString())
                }
            ).isDisposed
    }

}
