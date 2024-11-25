package com.example.hellocowcow.data.retrofit.mvxApi.response

import com.google.gson.annotations.SerializedName


data class Arguments (

  @SerializedName("transfers") var transfers : ArrayList<Transfers> = arrayListOf(),
  @SerializedName("receiver") var receiver : String? = null,
  @SerializedName("functionName") var functionName : String? = null,
  @SerializedName("functionArgs") var functionArgs   : ArrayList<String> = arrayListOf(),

  )