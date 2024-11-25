package com.example.hellocowcow.ui.viewmodels.activity

import com.example.hellocowcow.app.module.BaseViewModel
import com.example.hellocowcow.domain.models.DomainAccount
import com.example.hellocowcow.domain.repositories.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
  private val accountRepository: AccountRepository
) : BaseViewModel() {

  private var _currentAccount: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
  val currentAccount: StateFlow<UiState> get() = _currentAccount


  sealed class UiState {
    data object Loading : UiState()
    class Success(val data: DomainAccount) : UiState()
    class Error(val error: String) : UiState()
  }

  fun getAccount(
    address: String
  ) = accountRepository.getAccount(address)
    .subscribeBy(
      onSuccess = { account ->
        _currentAccount.value = UiState.Success(account)
      },
      onError = {
        _currentAccount.value = UiState.Error(it.toString())
      }
    ).addTo(disposable)

}