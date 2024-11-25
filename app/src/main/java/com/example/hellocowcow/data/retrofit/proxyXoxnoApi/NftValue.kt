package com.example.hellocowcow.data.retrofit.proxyXoxnoApi

import com.google.gson.annotations.SerializedName


data class NftValue(

  @SerializedName("floorValue") var floorValue: Double? = null,
  @SerializedName("avgValue") var avgValue: Double? = null,
  @SerializedName("maxValue") var maxValue: Double? = null,
  @SerializedName("collectionFp") var collectionFp: Double? = null

)