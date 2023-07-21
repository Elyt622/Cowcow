package com.example.hellocowcow.data.repositories

import com.example.hellocowcow.data.network.api.MvxApi
import com.example.hellocowcow.data.response.mvxApi.RewardRequest
import com.example.hellocowcow.domain.models.DomainReward
import com.example.hellocowcow.domain.models.DomainToken
import com.example.hellocowcow.domain.repositories.TokenRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val mvxApi: MvxApi
) : TokenRepository {

    override fun getToken(
        id: String
    ): Single<DomainToken> =
        mvxApi.getToken(id).map { it.toDomain() }

    override
    fun getTotalRewardsToCollect(
        rewardRequest: RewardRequest
    ) : Single<DomainReward> =
        mvxApi.getTotalRewardsToCollect(rewardRequest)
            .map { it.toDomain() }

}