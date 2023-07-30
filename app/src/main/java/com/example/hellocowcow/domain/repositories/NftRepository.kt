package com.example.hellocowcow.domain.repositories

import com.example.hellocowcow.data.response.mvxApi.RewardRequest
import com.example.hellocowcow.data.response.mvxApi.RewardResponse
import com.example.hellocowcow.data.response.xoxnoApi.CollectionResponse
import com.example.hellocowcow.domain.models.DomainNft
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface NftRepository {

    fun getAllCowsInWallet(
        address: String
    ) : Single<List<DomainNft>>

    fun getNftMvx(
        identifier: String
    ) : Single<DomainNft>

    fun getAllDataUsers(
        request: RewardRequest
    ) : Observable<RewardResponse>

    fun getCowsWithCollection(
        identifiers: String,
        size: Int,
        from: Int
    ) : Single<List<DomainNft>>

    fun getNftXoxno(
        identifier: String
    ) : Single<DomainNft>

    fun getCowsListing(
        address: String
    ): Observable<CollectionResponse>

    fun getCowsInWallet(
        address: String
    ): Observable<CollectionResponse>
}