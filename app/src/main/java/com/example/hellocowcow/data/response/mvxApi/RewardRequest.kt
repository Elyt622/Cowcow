package com.example.hellocowcow.data.response.mvxApi

import com.google.gson.annotations.SerializedName


data class RewardRequest (

    @SerializedName("scAddress" ) var scAddress : String?           = null,
    @SerializedName("funcName"  ) var funcName  : String?           = null,
    @SerializedName("value"     ) var value     : String?           = null,
    @SerializedName("args"      ) var args      : ArrayList<String> = arrayListOf(),
    @SerializedName("caller" ) var caller : String?           = null
    )