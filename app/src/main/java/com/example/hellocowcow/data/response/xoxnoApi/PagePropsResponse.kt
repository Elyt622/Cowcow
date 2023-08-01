package com.example.hellocowcow.data.response.xoxnoApi

import com.google.gson.annotations.SerializedName

data class PagePropsResponse (
    @SerializedName("SEO"                  ) var SEO                  : SEOResponse?              = SEOResponse(),
    @SerializedName("fallBackFloor"        ) var fallBackFloor        : Double?           = null,
    @SerializedName("profileFallback"      ) var profileFallback      : ProfileFallbackResponse?  = ProfileFallbackResponse(),
    @SerializedName("listedNFTs"           ) var listedNFTs           : Int?              = null,
    @SerializedName("stakingPoolsFallback" ) var stakingPoolsFallback : ArrayList<String> = arrayListOf(),
    @SerializedName("id"                   ) var id                   : String?           = null,
    @SerializedName("holdersCount"         ) var holdersCount         : Int?              = null
)
