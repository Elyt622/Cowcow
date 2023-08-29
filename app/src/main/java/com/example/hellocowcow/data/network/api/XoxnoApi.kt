package com.example.hellocowcow.data.network.api

import com.example.hellocowcow.data.retrofit.xoxnoApi.StatsCollection
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface XoxnoApi {

    @GET("/_next/data/yCfuOGT0VGpaXK22KK71X/collection/{collection}.json")
    fun getStatsCollection(
        @Path("collection") collection: String
    ): Observable<StatsCollection>
}
