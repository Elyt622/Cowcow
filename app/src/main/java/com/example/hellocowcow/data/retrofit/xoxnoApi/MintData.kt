package com.example.hellocowcow.data.retrofit.xoxnoApi

import com.google.gson.annotations.SerializedName

data class MintData(
  @SerializedName("totalMintEgldVolume") var totalMintEgldVolume: Int? = null,
  @SerializedName("weekMintEgldVolume") var weekMintEgldVolume: Int? = null,
  @SerializedName("dayMintEgldVolume") var dayMintEgldVolume: Int? = null
)