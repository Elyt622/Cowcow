package com.example.hellocowcow.data.response.mvxApi

import com.example.hellocowcow.domain.DomainModelConvertible
import com.example.hellocowcow.domain.models.DomainReward
import com.google.gson.annotations.SerializedName
import java.math.BigInteger
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

data class RewardResponse (

    @SerializedName("returnData") var returnData : ArrayList<String> = arrayListOf()

) : DomainModelConvertible<DomainReward> {


    override fun toDomain()
    : DomainReward {
        @OptIn(ExperimentalEncodingApi::class)
        val decoded: ByteArray = Base64.decode(returnData[0])
        return DomainReward(
            byteArrayToNumber(decoded)
        )
    }

    private fun byteArrayToNumber(byteArray: ByteArray): String {
        val bigInteger = BigInteger(1, byteArray) // Using BigInteger(byteArray) is deprecated
        val decimalFactor = BigInteger.TEN.pow(18)
        val result = bigInteger.divide(decimalFactor)
        return result.toString()
    }

}