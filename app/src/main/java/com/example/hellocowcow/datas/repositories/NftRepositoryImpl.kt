package com.example.hellocowcow.datas.repositories

import com.example.hellocowcow.datas.network.api.MvxApi
import com.example.hellocowcow.datas.response.mvxApi.NftResponse
import com.example.hellocowcow.domain.repositories.NftRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class NftRepositoryImpl @Inject constructor(
    val mvxApi: MvxApi
) : NftRepository {

    override fun getAllCowsInWallet(
        address: String
    ): Single<List<NftResponse>> =
        mvxApi.getAllCowsInWallet(address)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

}