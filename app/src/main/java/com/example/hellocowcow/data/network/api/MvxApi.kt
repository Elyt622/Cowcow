package com.example.hellocowcow.data.network.api

import com.example.hellocowcow.data.response.mvxApi.AccountResponse
import com.example.hellocowcow.data.response.mvxApi.NftResponse
import com.example.hellocowcow.data.response.mvxApi.RewardRequest
import com.example.hellocowcow.data.response.mvxApi.RewardResponse
import com.example.hellocowcow.data.response.mvxApi.TokenResponse
import com.example.hellocowcow.data.response.mvxApi.TransactionsResponse
import com.example.hellocowcow.data.response.mvxApi.TransactionRequest
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MvxApi {

    @GET("/tokens/{identifier}")
    fun getToken(
        @Path("identifier") identifier: String
    ) : Single<TokenResponse>

    @GET("/nfts/{identifier}")
    fun getNft(
        @Path("identifier") identifier: String
    ) : Single<NftResponse>

    @GET ("/accounts/{address}/nfts?collections=COW-cd463d")
    fun getAllCowsInWallet(
        @Path("address") address: String
    ) : Single<List<NftResponse>>

    @GET("/collections/COW-cd463d/nfts")
    fun getCowsWithCollection(
        @Query("identifiers") identifiers: String,
        @Query("size") size: Int,
        @Query("from") from: Int
    ) : Single<List<NftResponse>>

    @GET("/accounts/{address}?withGuardianInfo=true")
    fun getAccount(
        @Path("address") address: String
    ) : Single<AccountResponse>

    @GET("/accounts/erd1qqqqqqqqqqqqqpgqqgzzsl0re9e3u0t3mhv3jwg6zu63zssd7yqs3uu9jk/nfts/count?collection=COW-cd463d")
    fun getStakingCowsCount() : Single<Int>

    @GET("/collections/TICKET-231cd2/nfts/count?traits=Chapter%204:Used")
    fun getTicketsUsedCount()
    : Single<Int>

    @POST("/query")
    fun getTotalRewardsToCollect(
        @Body rewardRequest: RewardRequest
    ) : Single<RewardResponse>

    @POST("/query")
    fun getAllDataUsers(
        @Body request: RewardRequest
    ) : Observable<RewardResponse>

    @POST("/transactions")
    fun sendTransaction(
        @Body tx: TransactionRequest
    ) : Observable<TransactionsResponse>

}
