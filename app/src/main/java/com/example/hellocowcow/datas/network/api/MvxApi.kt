package com.example.hellocowcow.datas.network.api

import com.example.hellocowcow.datas.response.TokenResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Singleton

interface MvxApi {

    @GET("/tokens/{identifier}")
    fun getToken(
        @Path("identifier") identifier: String
    ) : Single<TokenResponse>

}