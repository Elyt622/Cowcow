package com.example.hellocowcow.data.network.api

import com.example.hellocowcow.data.response.xoxnoApi.StatsCollectionResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface XoxnoApi {

    @GET("_next/data/PHcMx2PzyPQCiHo3z_avH/collection/{collection}.json")
    fun getStatsCollection(
        @Path("collection") collection: String,
        @Query("id") id: String
    ): Observable<StatsCollectionResponse>
}
