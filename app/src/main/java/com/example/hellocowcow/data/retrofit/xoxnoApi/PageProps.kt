package com.example.hellocowcow.data.retrofit.xoxnoApi

import com.google.gson.annotations.SerializedName

data class PageProps (
    @SerializedName("SEO"                  ) var SEO                  : SEO?              = SEO(),
    @SerializedName("fallBackFloor"        ) var fallBackFloor        : Double?           = null,
    @SerializedName("profileFallback"      ) var profileFallback      : ProfileFallback?  = ProfileFallback(),
    @SerializedName("listedNFTs"           ) var listedNFTs           : Int?              = null,
    @SerializedName("stakingPoolsFallback" ) var stakingPoolsFallback : ArrayList<String> = arrayListOf(),
    @SerializedName("id"                   ) var id                   : String?           = null,
    @SerializedName("holdersCount"         ) var holdersCount         : Int?              = null
)
