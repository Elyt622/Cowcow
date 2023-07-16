package com.example.hellocowcow.datas.repositories

import com.example.hellocowcow.datas.network.api.CCToolsApi
import com.example.hellocowcow.datas.response.ccToolsApi.CCToolsResponse
import com.example.hellocowcow.domain.repositories.CCToolsRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


class CCToolsRepositoryImpl @Inject constructor(
    private val ccToolsApi: CCToolsApi
) : CCToolsRepository{

    override
    fun getInfoToday(): Single<CCToolsResponse> = ccToolsApi.getInfoToday()

    override
    fun getInfoYesterday(): Single<CCToolsResponse> = ccToolsApi.getInfoYesterday()

    override
    fun getInfoAth(): Single<CCToolsResponse> = ccToolsApi.getInfoAth()
}