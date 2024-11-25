package com.example.hellocowcow.data.retrofit.proxyXoxnoApi

import com.google.gson.annotations.SerializedName


data class OriginalMedia(

  @SerializedName("contentType") var contentType: String? = null,
  @SerializedName("contentLength") var contentLength: Int? = null

)