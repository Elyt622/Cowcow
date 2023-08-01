package com.example.hellocowcow.data.response.xoxnoApi

import com.google.gson.annotations.SerializedName

data class OtherResponse (
    @SerializedName("nftCount"     ) var nftCount     : Int? = null,
    @SerializedName("followCount"  ) var followCount  : Int? = null,
    @SerializedName("holdersCount" ) var holdersCount : Int? = null
)