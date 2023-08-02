package com.example.hellocowcow.ui.viewmodels.screen.collection

import androidx.lifecycle.ViewModel
import com.example.hellocowcow.domain.models.DomainCollection
import com.example.hellocowcow.domain.repositories.NftRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val nftRepository: NftRepository
) : ViewModel() {

    sealed class UiState {
        object Loading : UiState()
        data class Success(val data: DomainCollection) : UiState()
        data class Error(val error: String) : UiState()
    }

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    init {
        getCowsCollectionStats()
    }

    private fun getCowsCollectionStats() {
        Observable.zip(
            nftRepository.getStakingCowsCount().toObservable(),
            nftRepository.getUpgradedCowsCount(),
            nftRepository.getStatsCollection(
                "COW-cd463d"
            ).map { it.pageProps!! }
        ) { stakingCount, upgradedCount, statsCollection ->
            DomainCollection(
                stakedCount = stakingCount,
                holdersCount = statsCollection.holdersCount,
                listedCount = statsCollection.listedNFTs,
                floorPrice = statsCollection.fallBackFloor,
                totalUpgradedCount = upgradedCount.count,
                athEgldPrice = statsCollection.profileFallback?.statistics?.tradeData?.athEgldPrice,
                totalTrades = statsCollection.profileFallback?.statistics?.tradeData?.totalTrades,
                followAccountsCount = statsCollection.profileFallback?.statistics?.other?.followCount,
                dayEgldVolume = statsCollection.profileFallback?.statistics?.tradeData?.dayEgldVolume,
                weekEgldVolume = statsCollection.profileFallback?.statistics?.tradeData?.weekEgldVolume,
                totalEgldVolume = statsCollection.profileFallback?.statistics?.tradeData?.totalEgldVolume
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
