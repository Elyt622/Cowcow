package com.example.hellocowcow.domain.repositories

import com.example.hellocowcow.domain.models.DomainAccount
import io.reactivex.rxjava3.core.Single

interface AccountRepository {

  fun getAccount(
    address: String
  ): Single<DomainAccount>

}