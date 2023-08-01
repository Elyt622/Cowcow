package com.example.hellocowcow.data.response.xoxnoApi

import com.google.gson.annotations.SerializedName

data class StatsCollectionResponse (
    @SerializedName("pageProps" ) var pageProps : PagePropsResponse? = PagePropsResponse(),
)