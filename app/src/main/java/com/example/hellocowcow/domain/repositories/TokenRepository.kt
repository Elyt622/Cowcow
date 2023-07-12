package com.example.hellocowcow.domain.repositories

import com.example.hellocowcow.domain.models.DomainToken
import io.reactivex.rxjava3.core.Single

interface TokenRepository {

    fun getToken(id: String) : Single<DomainToken>

}