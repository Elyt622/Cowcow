package com.example.hellocowcow.data.network.api

import com.example.hellocowcow.data.retrofit.proxyXoxnoApi.Collection
import com.example.hellocowcow.data.retrofit.proxyXoxnoApi.Nft
import com.example.hellocowcow.data.retrofit.proxyXoxnoApi.Upgraded
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ProxyXoxnoApi {

    @GET("/nfts/{identifier}")
    fun getNft(
        @Path("identifier") identifier: String
    ) : Single<Nft>

    @GET("/accounts/{address}/listings")
    fun getCowsListing(
        @Path("address") address: String
    ) : Observable<Collection>

    @GET("/accounts/{address}/inventory")
    fun getCowsInWallet(
        @Path("address") address : String
    ) : Observable<Collection>

    @GET("searchNFTs/eyJmaWx0ZXJzIjp7Imhhc1NlY29uZE5GVCI6dHJ1ZX0sImNvbGxlY3Rpb24iOiJDT1ctY2Q0NjNkIiwidG9wIjoxfQ==")
    fun getUpgradedCowsCount()
    : Observable<Upgraded>

}
