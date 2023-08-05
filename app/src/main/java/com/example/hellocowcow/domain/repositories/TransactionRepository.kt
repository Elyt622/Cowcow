package com.example.hellocowcow.domain.repositories

import com.example.hellocowcow.data.response.mvxApi.TransactionRequest
import com.example.hellocowcow.data.response.mvxApi.TransactionsResponse
import io.reactivex.rxjava3.core.Observable

interface TransactionRepository {

    fun sendTransaction(
        tx: TransactionRequest
    ) : Observable<TransactionsResponse>

}