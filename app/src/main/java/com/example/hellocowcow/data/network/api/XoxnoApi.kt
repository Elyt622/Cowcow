package com.example.hellocowcow.data.network.api

import com.example.hellocowcow.data.retrofit.xoxnoApi.BuildId
import com.example.hellocowcow.data.retrofit.xoxnoApi.StatsCollection
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface XoxnoApi {

  @GET("/_next/data/{dynamicId}/collection/{collection}.json")
  fun getStatsCollection(
    @Path("dynamicId") dynamicId: String,
    @Path("collection") collection: String
  ): Observable<StatsCollection>

  @GET("/api/build-id")
  fun getDynamicId() : Observable<BuildId>
}
