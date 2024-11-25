package com.example.hellocowcow.data.retrofit.xoxnoApi

import com.google.gson.annotations.SerializedName

data class SEO(
  @SerializedName("title") var title: String? = null,
  @SerializedName("description") var description: String? = null,
  @SerializedName("url") var url: String? = null
)
