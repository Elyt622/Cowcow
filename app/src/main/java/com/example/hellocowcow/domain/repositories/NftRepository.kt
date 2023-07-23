package com.example.hellocowcow.domain.repositories

import com.example.hellocowcow.data.response.mvxApi.NftResponse
import com.example.hellocowcow.data.response.mvxApi.RewardRequest
import com.example.hellocowcow.data.response.mvxApi.RewardResponse
import com.example.hellocowcow.data.response.xoxnoApi.CollectionResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface NftRepository {

    fun getAllCowsInWallet(
        address: String
    ) : Single<List<NftResponse>>

    fun getNftMvx(
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

    fun getNftXoxno(
        identifier: String
    ) : Single<com.example.hellocowcow.data.response.xoxnoApi.NftResponse>

    fun getCowsListing(
        address: String
    ): Observable<CollectionResponse>
}