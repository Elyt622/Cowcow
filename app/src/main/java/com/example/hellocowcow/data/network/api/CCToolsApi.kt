package com.example.hellocowcow.data.network.api

import com.example.hellocowcow.data.response.ccToolsApi.CCToolsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface CCToolsApi {

    @GET("/today")
    fun getInfoToday() : Single<CCToolsResponse>

    @GET("/yesterday")
    fun getInfoYesterday() : Single<CCToolsResponse>

    @GET("/ath")
    fun getInfoAth() : Single<CCToolsResponse>
}
