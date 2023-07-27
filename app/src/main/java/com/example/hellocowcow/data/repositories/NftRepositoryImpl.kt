package com.example.hellocowcow.data.repositories

import com.example.hellocowcow.data.network.api.MvxApi
import com.example.hellocowcow.data.network.api.XoxnoApi
import com.example.hellocowcow.data.response.mvxApi.RewardRequest
import com.example.hellocowcow.data.response.mvxApi.RewardResponse
import com.example.hellocowcow.data.response.xoxnoApi.CollectionResponse
import com.example.hellocowcow.domain.models.DomainNft
import com.example.hellocowcow.domain.repositories.NftRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class NftRepositoryImpl @Inject constructor(
    val mvxApi: MvxApi,
    val xoxnoApi: XoxnoApi
) : NftRepository {

    override fun getAllCowsInWallet(
        address: String
    ): Single<List<DomainNft>> =
        mvxApi.getAllCowsInWallet(address)
            .toObservable()
            .flatMapIterable { it }
            .map { it.toDomain() }
            .toSortedList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun getNftMvx(
        identifier: String
    ): Single<DomainNft> =
        mvxApi.getNft(identifier)
            .map { it.toDomain() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override
    fun getAllDataUsers(
        request: RewardRequest
    ): Single<RewardResponse> =
        mvxApi.getAllDataUsers(request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override
    fun getCowsWithCollection(
        identifiers: String,
        size: Int,
        from: Int
    ): Single<List<DomainNft>> =
        mvxApi.getCowsWithCollection(identifiers, size, from)
            .toObservable()
            .flatMapIterable { it }
            .map { it.toDomain() }
            .toSortedList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override
    fun getNftXoxno(
        identifier: String
    ): Single<DomainNft> =
        xoxnoApi.getNft(identifier)
            .map { it.toDomain() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override
    fun getCowsListing(
        address: String
    ): Observable<CollectionResponse> =
        xoxnoApi.getCowsListing(address)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

}