package com.example.hellocowcow.datas.repositories

import com.example.hellocowcow.datas.network.api.MvxApi
import com.example.hellocowcow.domain.models.DomainTransaction
import com.example.hellocowcow.domain.repositories.TransactionRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val mvxApi: MvxApi
) : TransactionRepository {

    override
    fun getAllCowsStaked(
        address: String,
        stakingAddress: String
    ) : Single<List<DomainTransaction>> =
        mvxApi.getAllCowsStaked(
            address,
            stakingAddress
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toObservable()
            .flatMapIterable { it }
            .map { it.toDomain() }
            .toList()


    override
    fun getAllCowsUnStaked(
        address: String,
        stakingAddress: String
    ) : Single<List<DomainTransaction>> =
        mvxApi.getAllCowsUnStaked(
            address,
            stakingAddress
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toObservable()
            .flatMapIterable { it }
            .map { it.toDomain() }
            .toList()


}