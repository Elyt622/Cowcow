package com.example.hellocowcow.data.repositories

import com.example.hellocowcow.data.network.api.MvxApi
import com.example.hellocowcow.data.retrofit.mvxApi.request.Transaction
import com.example.hellocowcow.domain.models.DomainTransaction
import com.example.hellocowcow.domain.repositories.TransactionRepository
import com.example.hellocowcow.ui.viewmodels.util.MySchedulers
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
  private val mySchedulers: MySchedulers,
  private val mvxApi: MvxApi
) : TransactionRepository {

  override
  fun sendTransaction(
    tx: Transaction
  ) : Observable<DomainTransaction> =
    mvxApi.sendTransaction(tx)
      .map { it.toDomain() }
      .subscribeOn(mySchedulers.io)
      .observeOn(mySchedulers.main)

}