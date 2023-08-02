package com.example.hellocowcow.data.repositories

import com.example.hellocowcow.data.network.api.MvxApi
import com.example.hellocowcow.data.response.mvxApi.RewardRequest
import com.example.hellocowcow.domain.models.DomainReward
import com.example.hellocowcow.domain.models.DomainToken
import com.example.hellocowcow.domain.repositories.TokenRepository
import com.example.hellocowcow.ui.viewmodels.util.MySchedulers
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val mySchedulers: MySchedulers,
    private val mvxApi: MvxApi
) : TokenRepository {

    override fun getToken(
        id: String
    ): Single<DomainToken> =
        mvxApi.getToken(id).map { it.toDomain() }
            .subscribeOn(mySchedulers.io)
            .observeOn(mySchedulers.main)

    override
    fun getTotalRewardsToCollect(
        rewardRequest: RewardRequest
    ) : Single<DomainReward> =
        mvxApi.getTotalRewardsToCollect(rewardRequest)
            .map { it.toDomain() }
            .subscribeOn(mySchedulers.io)
            .observeOn(mySchedulers.main)

}