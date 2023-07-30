package com.example.hellocowcow.data.response.mvxApi

import com.example.hellocowcow.domain.DomainModelConvertible
import com.example.hellocowcow.domain.models.DomainAccount
import com.google.gson.annotations.SerializedName


data class AccountResponse (

    @SerializedName("address"         ) var address         : String? = null,
    @SerializedName("balance"         ) var balance         : String? = null,
    @SerializedName("nonce"           ) var nonce           : Int?    = null,
    @SerializedName("timestamp"       ) var timestamp       : Int?    = null,
    @SerializedName("shard"           ) var shard           : Int?    = null,
    @SerializedName("rootHash"        ) var rootHash        : String? = null,
    @SerializedName("txCount"         ) var txCount         : Int?    = null,
    @SerializedName("scrCount"        ) var scrCount        : Int?    = null,
    @SerializedName("username"        ) var username        : String? = null,
    @SerializedName("developerReward" ) var developerReward : String? = null

) : DomainModelConvertible<DomainAccount> {
    override fun toDomain()
    : DomainAccount =
        DomainAccount(
            address = address,
            username = username,
            balance = balance
        )

}