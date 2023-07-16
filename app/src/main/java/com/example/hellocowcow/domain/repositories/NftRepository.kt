package com.example.hellocowcow.domain.repositories

import com.example.hellocowcow.datas.response.mvxApi.NftResponse
import io.reactivex.rxjava3.core.Single

interface NftRepository {

    fun getAllCowsInWallet(
        address: String
    ) : Single<List<NftResponse>>

}