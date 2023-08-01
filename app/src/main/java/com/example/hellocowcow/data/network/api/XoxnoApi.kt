package com.example.hellocowcow.data.network.api

import com.example.hellocowcow.data.response.xoxnoApi.StatsCollectionResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface XoxnoApi {

    @GET("_next/data/PHcMx2PzyPQCiHo3z_avH/collection/COW-cd463d.json?id=COW-cd463d")
    fun getStatsCollection()
    : Observable<StatsCollectionResponse>
}
