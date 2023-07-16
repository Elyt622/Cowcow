package com.example.hellocowcow.datas.network.api

import com.example.hellocowcow.datas.response.mvxApi.NftResponse
import com.example.hellocowcow.datas.response.mvxApi.TokenResponse
import com.example.hellocowcow.datas.response.mvxApi.TransactionsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MvxApi {

    @GET("/tokens/{identifier}")
    fun getToken(
        @Path("identifier") identifier: String
    ) : Single<TokenResponse>

    @GET("/accounts/{address}/transactions?receiver={stakingAddress}&status=success&function=stake")
    fun getAllCowsStaked(
        @Path("address") address: String,
        @Path("stakingAddress") stakingAddress: String
    ) : Single<List<TransactionsResponse>>

    @GET("/accounts/{address}/transactions?receiver={stakingAddress}&status=success&function=unstake")
    fun getAllCowsUnStaked(
        @Path("address") address: String,
        @Path("stakingAddress") stakingAddress: String
    ) : Single<List<TransactionsResponse>>

    @GET ("/accounts/{address}/nfts?collections=COW-cd463d")
    fun getAllCowsInWallet(
        @Path("address") address: String
    ) : Single<List<NftResponse>>
}
