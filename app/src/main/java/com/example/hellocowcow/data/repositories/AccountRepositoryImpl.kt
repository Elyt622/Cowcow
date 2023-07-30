package com.example.hellocowcow.data.repositories

import com.example.hellocowcow.data.network.api.MvxApi
import com.example.hellocowcow.domain.models.DomainAccount
import com.example.hellocowcow.domain.repositories.AccountRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class AccountRepositoryImpl @Inject constructor(
    private val mvxApi: MvxApi
) : AccountRepository{
    override
    fun getAccount(
        address: String
    ): Single<DomainAccount> =
        mvxApi.getAccount(address)
            .map { it.toDomain() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

}