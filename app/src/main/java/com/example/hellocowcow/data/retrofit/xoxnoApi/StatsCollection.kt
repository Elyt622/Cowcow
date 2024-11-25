package com.example.hellocowcow.data.retrofit.xoxnoApi

import com.google.gson.annotations.SerializedName

data class StatsCollection(
  @SerializedName("pageProps") var pageProps: PageProps? = PageProps(),
)