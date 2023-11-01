package com.example.hellocowcow.data.repositories

import com.example.hellocowcow.data.network.api.MvxApi
import com.example.hellocowcow.data.network.api.ProxyXoxnoApi
import com.example.hellocowcow.data.network.api.XoxnoApi
import com.example.hellocowcow.data.retrofit.proxyXoxnoApi.Collection
import com.example.hellocowcow.data.retrofit.proxyXoxnoApi.Resources
import com.example.hellocowcow.data.retrofit.proxyXoxnoApi.Upgraded
import com.example.hellocowcow.data.retrofit.xoxnoApi.StatsCollection
import com.example.hellocowcow.domain.models.DomainNft
import com.example.hellocowcow.domain.repositories.NftRepository
import com.example.hellocowcow.ui.viewmodels.util.MySchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class NftRepositoryImpl @Inject constructor(
    val mySchedulers: MySchedulers,
    val mvxApi: MvxApi,
    val proxyXoxnoApi: ProxyXoxnoApi,
    val xoxnoApi: XoxnoApi
) : NftRepository {

    override fun getAllCowsInWallet(
        address: String
    ): Single<List<DomainNft>> =
        mvxApi.getAllCowsInWallet(address)
            .toObservable()
            .flatMapIterable { it }
            .map { it.toDomain() }
            .toList()
            .subscribeOn(mySchedulers.io)
            .observeOn(mySchedulers.main)

    override fun getNftMvx(
        identifier: String
    ): Single<DomainNft> =
        mvxApi.getNft(identifier)
            .map { it.toDomain() }
            .subscribeOn(mySchedulers.io)
            .observeOn(mySchedulers.main)

    override
    fun getAllDataUsers(
        request: com.example.hellocowcow.data.retrofit.mvxApi.request.Reward
    ): Observable<com.example.hellocowcow.data.retrofit.mvxApi.response.Reward> =
        mvxApi.getAllDataUsers(request)
            .subscribeOn(mySchedulers.io)
            .observeOn(mySchedulers.main)

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
            .toList()
            .subscribeOn(mySchedulers.io)
            .observeOn(mySchedulers.main)

    override
    fun getNftXoxno(
        identifier: String
    ): Single<DomainNft> =
        proxyXoxnoApi.getNft(identifier)
            .map { it.toDomain() }
            .subscribeOn(mySchedulers.io)
            .observeOn(mySchedulers.main)

    override
    fun getCowsListing(
        address: String
    ): Observable<Collection> =
        proxyXoxnoApi.getCowsListing(address)
            .subscribeOn(mySchedulers.io)
            .observeOn(mySchedulers.main)

    override
    fun getCowsInWallet(
        address: String
    ): Observable<Collection> =
        proxyXoxnoApi.getCowsInWallet(address)
            .subscribeOn(mySchedulers.io)
            .observeOn(mySchedulers.main)

    override
    fun getUpgradedCowsCount()
    : Observable<Upgraded> =
        proxyXoxnoApi.getUpgradedCowsCount()
            .subscribeOn(mySchedulers.io)
            .observeOn(mySchedulers.main)

    override
    fun getStakingCowsCount()
    : Single<Int> =
        mvxApi.getStakingCowsCount()
            .subscribeOn(mySchedulers.io)
            .observeOn(mySchedulers.main)

    override
    fun getStatsCollection(
        collection: String
    ): Observable<StatsCollection> =
        xoxnoApi.getDynamicId().flatMap { dynamicId ->
            xoxnoApi.getStatsCollection(
                dynamicId.buildId.toString(),
                collection
            ).subscribeOn(mySchedulers.io)
        }.subscribeOn(mySchedulers.io)
            .observeOn(mySchedulers.main)


    override
    fun getTicketsUsedCount()
    : Single<Int> =
        mvxApi.getTicketsUsedCount()
            .subscribeOn(mySchedulers.io)
            .observeOn(mySchedulers.main)

    override
    fun getLastTenSold()
    : Observable<ArrayList<Resources>> =
        proxyXoxnoApi.getLastTenSold()
            .map { it.resources }
            .subscribeOn(mySchedulers.io)
            .observeOn(mySchedulers.main)


}