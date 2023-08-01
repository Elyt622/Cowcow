package com.example.hellocowcow.ui.viewmodels.screen.collection

import androidx.lifecycle.ViewModel
import com.example.hellocowcow.data.network.api.MvxApi
import com.example.hellocowcow.data.network.api.ProxyXoxnoApi
import com.example.hellocowcow.data.network.api.XoxnoApi
import com.example.hellocowcow.domain.models.DomainCollection
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val proxyXoxnoApi: ProxyXoxnoApi,
    private val mvxApi: MvxApi,
    private val xoxnoApi: XoxnoApi
) : ViewModel() {

    sealed class UiState {
        object Loading : UiState()
        data class Success(val data: DomainCollection) : UiState()
        data class Error(val error: String) : UiState()
    }

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    init {
        getCollectionStats()
    }

    private fun getCollectionStats() {
        Observable.zip(
            mvxApi.getStakingCowsCount().toObservable(),
            proxyXoxnoApi.getUpgradedCowsCount(),
            xoxnoApi.getStatsCollection()
        ) { stakingCount,
            upgradedCount,
            statsCollection ->
            DomainCollection(
                stakedCows = stakingCount,
                holdersCows = statsCollection.pageProps?.holdersCount,
                listedCows = statsCollection.pageProps?.listedNFTs,
                floorPriceCows = statsCollection.pageProps?.fallBackFloor,
                totalUpgradedCows = upgradedCount.count,
                athEgldPrice = statsCollection.pageProps?.profileFallback?.statistics?.tradeData?.athEgldPrice,
                totalTrades = statsCollection.pageProps?.profileFallback?.statistics?.tradeData?.totalTrades,
                followAccounts = statsCollection.pageProps?.profileFallback?.statistics?.other?.followCount,
                dayEgldVolume = statsCollection.pageProps?.profileFallback?.statistics?.tradeData?.dayEgldVolume,
                weekEgldVolume = statsCollection.pageProps?.profileFallback?.statistics?.tradeData?.weekEgldVolume,
                totalEgldVolume = statsCollection.pageProps?.profileFallback?.statistics?.tradeData?.totalEgldVolume
            )
        }.subscribeBy (
            onNext = {
                _uiState.value = UiState.Success(it)
            },
            onError = {
                _uiState.value = UiState.Error(it.message.toString())
            }
        ).isDisposed
    }

}
