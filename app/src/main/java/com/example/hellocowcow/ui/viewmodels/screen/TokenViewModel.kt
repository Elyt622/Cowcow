package com.example.hellocowcow.ui.viewmodels.screen

import androidx.lifecycle.ViewModel
import com.example.hellocowcow.data.response.mvxApi.RewardRequest
import com.example.hellocowcow.domain.models.DomainReward
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

    private val _uiStateT: MutableStateFlow<UiStateToken> = MutableStateFlow(UiStateToken.Loading)
    val uiStateT: StateFlow<UiStateToken> = _uiStateT

    private val _uiStateR: MutableStateFlow<UiStateReward> = MutableStateFlow(UiStateReward.Loading)
    val uiStateR: StateFlow<UiStateReward> = _uiStateR

    sealed class UiStateToken {
        object Loading : UiStateToken()
        data class Success(val data: DomainToken) : UiStateToken()
        data class Error(val error: String) : UiStateToken()
    }

    sealed class UiStateReward {
        object Loading : UiStateReward()
        data class Success(val data: DomainReward) : UiStateReward()
        data class Error(val error: String) : UiStateReward()
    }

    init {
        getTokenInfo()
        getTotalRewardsToCollect()
    }

    private fun getTokenInfo() {
        tokenApi.getToken("MOOVE-875539")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy (
                onSuccess = { token ->
                    _uiStateT.value = UiStateToken.Success(token)
                },
                onError = { error ->
                    _uiStateT.value = UiStateToken.Error(error.message.toString())
                }
            ).isDisposed
    }

    private fun getTotalRewardsToCollect() =
        tokenApi.getTotalRewardsToCollect(
            RewardRequest(
                "erd1qqqqqqqqqqqqqpgqqgzzsl0re9e3u0t3mhv3jwg6zu63zssd7yqs3uu9jk",
                "getTotalRewardsToCollect",
                "0",
                arrayListOf()
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy (
                onSuccess = { unclaimedMoove ->
                    _uiStateR.value = UiStateReward.Success(unclaimedMoove)
                },
                onError = { error ->
                    _uiStateR.value = UiStateReward.Error(error.message.toString())
                }
                ).isDisposed
            }

