package com.example.hellocowcow.data.repositories

import com.example.hellocowcow.data.network.api.MvxApi
import com.example.hellocowcow.domain.models.DomainAccount
import com.example.hellocowcow.domain.repositories.AccountRepository
import com.example.hellocowcow.ui.viewmodels.util.MySchedulers
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


class AccountRepositoryImpl @Inject constructor(
  private val mySchedulers: MySchedulers,
  private val mvxApi: MvxApi
) : AccountRepository{
  override
  fun getAccount(
    address: String
  ): Single<DomainAccount> =
    mvxApi.getAccount(address)
      .map { it.toDomain() }
      .subscribeOn(mySchedulers.io)
      .observeOn(mySchedulers.main)

}