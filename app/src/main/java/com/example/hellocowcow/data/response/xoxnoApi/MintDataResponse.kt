package com.example.hellocowcow.data.response.xoxnoApi

import com.google.gson.annotations.SerializedName

data class MintDataResponse (
    @SerializedName("totalMintEgldVolume" ) var totalMintEgldVolume : Int? = null,
    @SerializedName("weekMintEgldVolume"  ) var weekMintEgldVolume  : Int? = null,
    @SerializedName("dayMintEgldVolume"   ) var dayMintEgldVolume   : Int? = null
)