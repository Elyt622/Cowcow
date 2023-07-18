package com.example.hellocowcow.domain.repositories

import com.example.hellocowcow.datas.response.mvxApi.NftResponse
import com.example.hellocowcow.datas.response.mvxApi.RewardRequest
import com.example.hellocowcow.datas.response.mvxApi.RewardResponse
import io.reactivex.rxjava3.core.Single

interface NftRepository {

    fun getAllCowsInWallet(
        address: String
    ) : Single<List<NftResponse>>

    fun getNft(
        identifier: String
    ) : Single<NftResponse>

    fun getAllDataUsers(
        request: RewardRequest
    ) : Single<RewardResponse>

    fun getCowsWithCollection(
        identifiers: String,
        size: Int,
        from: Int
    ) : Single<List<NftResponse>>
}