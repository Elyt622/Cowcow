package com.example.hellocowcow.datas.network.api

import com.example.hellocowcow.datas.response.mvxApi.NftResponse
import com.example.hellocowcow.datas.response.mvxApi.RewardRequest
import com.example.hellocowcow.datas.response.mvxApi.RewardResponse
import com.example.hellocowcow.datas.response.mvxApi.TokenResponse
import com.example.hellocowcow.datas.response.mvxApi.TransactionsResponse
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

    @GET("/accounts/{address}/transactions?&status=success")
    fun getAllCowsWithFunction(
        @Path("address") address: String,
        @Query("receiver") receiver: String,
        @Query("function") function: String
    ) : Single<List<TransactionsResponse>>

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

    @POST("/query")
    fun getTotalRewardsToCollect(
        @Body rewardRequest: RewardRequest
    ) : Single<RewardResponse>

    @POST("/query")
    fun getAllDataUsers(
        @Body request: RewardRequest
    ) : Single<RewardResponse>


}
