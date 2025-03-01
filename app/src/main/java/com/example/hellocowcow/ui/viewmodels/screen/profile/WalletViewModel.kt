package com.example.hellocowcow.ui.viewmodels.screen.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.hellocowcow.app.module.BaseViewModel
import com.example.hellocowcow.domain.models.DomainNft
import com.example.hellocowcow.domain.repositories.NftRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
  private val nftRepository: NftRepository
) : BaseViewModel() {

  private val _address = mutableStateOf("")
  val address: State<String> get() = _address

  fun setAddress(value: String) {
    _address.value = value
  }

  sealed class UiState {
    data object NoData : UiState()
    data object Loading : UiState()
    data class Success(val data: List<DomainNft>) : UiState()
    data class Error(val error: String) : UiState()
  }

  private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
  val uiState: StateFlow<UiState> = _uiState

  fun getCowsInWallet() =
    nftRepository.getCowsInWallet(address.value)
      .map {
        it.groupedByCollection
      }
      .flatMapIterable { it }
      .filter { collection -> collection.ticker == "COW-cd463d" }
      .toList()
      .map {
        if (it.isNotEmpty())
          it[0].nfts
        else
          listOf()
      }
      .toObservable()
      .flatMapIterable { it }
      .map { it.toDomain() }
      .toList()
      .subscribeBy(
        onSuccess = {
          if (it.isNotEmpty())
            _uiState.value = UiState.Success(it)
          else
            _uiState.value = UiState.NoData
        },
        onError = {
          _uiState.value = UiState.Error(it.message.toString())
        }
      ).addTo(disposable)

}
