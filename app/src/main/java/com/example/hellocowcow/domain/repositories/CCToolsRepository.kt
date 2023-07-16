package com.example.hellocowcow.domain.repositories

import com.example.hellocowcow.datas.response.ccToolsApi.CCToolsResponse
import io.reactivex.rxjava3.core.Single

interface CCToolsRepository {

    fun getInfoToday() : Single<CCToolsResponse>

    fun getInfoYesterday() : Single<CCToolsResponse>

    fun getInfoAth(): Single<CCToolsResponse>
}