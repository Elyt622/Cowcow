package com.example.hellocowcow.datas.repositories

import com.example.hellocowcow.datas.network.api.MvxApi
import com.example.hellocowcow.datas.network.api.XoxnoApi
import com.example.hellocowcow.datas.response.mvxApi.NftResponse
import com.example.hellocowcow.datas.response.mvxApi.RewardRequest
import com.example.hellocowcow.datas.response.mvxApi.RewardResponse
import com.example.hellocowcow.domain.repositories.NftRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class NftRepositoryImpl @Inject constructor(
    val mvxApi: MvxApi,
    val xoxnoApi: XoxnoApi
) : NftRepository {

    override fun getAllCowsInWallet(
        address: String
    ): Single<List<NftResponse>> =
        mvxApi.getAllCowsInWallet(address)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun getNftMvx(
        identifier: String
    ) : Single<NftResponse> =
        mvxApi.getNft(identifier)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override
    fun getAllDataUsers(
        request: RewardRequest
    ) : Single<RewardResponse> =
        mvxApi.getAllDataUsers(request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override
    fun getCowsWithCollection(
        identifiers: String,
        size: Int,
        from: Int
    ) : Single<List<NftResponse>> =
        mvxApi.getCowsWithCollection(identifiers, size, from)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override
    fun getNftXoxno(
        identifier: String
    ) : Single<com.example.hellocowcow.datas.response.xoxnoApi.NftResponse> =
        xoxnoApi.getNft(identifier)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}