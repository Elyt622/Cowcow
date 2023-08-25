package com.example.hellocowcow.domain.repositories

import com.example.hellocowcow.data.retrofit.mvxApi.request.Transaction
import com.example.hellocowcow.domain.models.DomainTransaction
import io.reactivex.rxjava3.core.Observable

interface TransactionRepository {

    fun sendTransaction(
        tx: Transaction
    ) : Observable<DomainTransaction>

}