package com.example.hellocowcow.data.retrofit.mvxApi.response

import com.google.gson.annotations.SerializedName


data class Transfers (

  @SerializedName("type") var type : String? = null,
  @SerializedName("name") var name : String? = null,
  @SerializedName("ticker") var ticker : String? = null,
  @SerializedName("svgUrl") var svgUrl : String? = null,
  @SerializedName("collection" ) var collection : String? = null,
  @SerializedName("identifier" ) var identifier : String? = null,
  @SerializedName("token") var token : String? = null,
  @SerializedName("decimals") var decimals : Int? = null,
  @SerializedName("value") var value : String? = null

)