package com.example.hellocowcow.data.retrofit.proxyXoxnoApi

import com.google.gson.annotations.SerializedName


data class Activity(

  @SerializedName("resources") var resources: ArrayList<Resources> = arrayListOf(),
  @SerializedName("hasMoreResults") var hasMoreResults: Boolean? = null

)