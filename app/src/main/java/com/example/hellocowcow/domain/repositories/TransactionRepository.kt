package com.example.hellocowcow.domain.repositories

import com.example.hellocowcow.domain.models.DomainTransaction
import io.reactivex.rxjava3.core.Single

interface TransactionRepository {

    fun getAllCowsStaked(
        address: String,
        stakingAddress: String
    ) : Single<List<DomainTransaction>>

    fun getAllCowsUnStaked(
        address: String,
        stakingAddress: String
    ) : Single<List<DomainTransaction>>

}