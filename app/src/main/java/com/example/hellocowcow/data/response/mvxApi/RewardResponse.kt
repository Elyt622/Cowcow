package com.example.hellocowcow.data.response.mvxApi

import com.example.hellocowcow.domain.DomainModelConvertible
import com.example.hellocowcow.domain.models.DomainReward
import com.google.gson.annotations.SerializedName
import java.math.BigInteger
import java.math.RoundingMode
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

data class RewardResponse (

    @SerializedName("returnData"     ) var returnData     : ArrayList<String> = arrayListOf()

) : DomainModelConvertible<DomainReward> {


    override fun toDomain()
    : DomainReward {
        @OptIn(ExperimentalEncodingApi::class)
        val decoded: ByteArray = Base64.decode(returnData[0])
        return DomainReward(
            BigInteger(decoded)
                .toBigDecimal(18)
                .setScale(0, RoundingMode.HALF_UP)
                .toEngineeringString()
        )
    }
}