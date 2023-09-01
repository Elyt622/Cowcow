package com.example.hellocowcow.data.retrofit.mvxApi.response

import com.example.hellocowcow.domain.DomainModelConvertible
import com.example.hellocowcow.domain.models.DomainAccount
import com.google.gson.annotations.SerializedName


data class Account (

    @SerializedName("address") var address : String = "",
    @SerializedName("balance") var balance : String? = null,
    @SerializedName("nonce") var nonce : Int? = null,
    @SerializedName("timestamp") var timestamp : Int? = null,
    @SerializedName("shard") var shard : Int? = null,
    @SerializedName("rootHash") var rootHash : String? = null,
    @SerializedName("txCount") var txCount : Int? = null,
    @SerializedName("scrCount") var scrCount : Int? = null,
    @SerializedName("username") var username : String = "",
    @SerializedName("developerReward" ) var developerReward : String? = null,
    @SerializedName("activeGuardianAddress") var activeGuardianAddress : String = "",
    @SerializedName("isGuarded") var isGuarded : Boolean = false

) : DomainModelConvertible<DomainAccount> {
    override fun toDomain()
    : DomainAccount =
        DomainAccount(
            address = address,
            username = username,
            balance = balance,
            nonce = nonce!!,
            txCount = txCount,
            timestamp = timestamp,
            shard = shard,
            rootHash = rootHash,
            developerReward = developerReward,
            activeGuardianAddress = activeGuardianAddress,
            isGuarded = isGuarded
        )

}