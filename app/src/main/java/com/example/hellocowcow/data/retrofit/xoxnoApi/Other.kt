package com.example.hellocowcow.data.retrofit.xoxnoApi

import com.google.gson.annotations.SerializedName

data class Other(
  @SerializedName("nftCount") var nftCount: Int? = null,
  @SerializedName("followCount") var followCount: Int? = null,
  @SerializedName("holdersCount") var holdersCount: Int? = null
)