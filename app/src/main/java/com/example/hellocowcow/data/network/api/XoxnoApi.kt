package com.example.hellocowcow.data.network.api

import com.example.hellocowcow.data.response.xoxnoApi.NftResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface XoxnoApi {

    @GET("/nfts/{identifier}")
    fun getNft(
        @Path("identifier") identifier: String
    ) : Single<NftResponse>

}
