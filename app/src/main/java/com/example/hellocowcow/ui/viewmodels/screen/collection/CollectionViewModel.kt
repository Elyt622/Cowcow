package com.example.hellocowcow.ui.viewmodels.screen.collection

import androidx.lifecycle.ViewModel
import com.example.hellocowcow.data.response.ccToolsApi.CCToolsResponse
import com.example.hellocowcow.domain.repositories.CCToolsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val ccToolsRepository: CCToolsRepository
) : ViewModel() {

    sealed class UiState {
        object Loading : UiState()
        data class Success(val data: CCToolsResponse) : UiState()
        data class Error(val error: String) : UiState()
    }

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    init {
        getInfoToday()
    }

    private fun getInfoToday () {
        ccToolsRepository.getInfoToday()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    _uiState.value = UiState.Success(it)
                },
                onError = {
                    _uiState.value = UiState.Error(it.message.toString())
                }
            ).isDisposed
    }

}
