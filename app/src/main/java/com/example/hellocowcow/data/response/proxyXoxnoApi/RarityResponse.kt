package com.example.hellocowcow.data.response.proxyXoxnoApi

import com.google.gson.annotations.SerializedName


data class RarityResponse (

    @SerializedName("avgRarity"         ) var avgRarity         : String? = null,
    @SerializedName("statRarity"        ) var statRarity        : String? = null,
    @SerializedName("rarityScore"       ) var rarityScore       : Double? = null,
    @SerializedName("rarityScoreNormed" ) var rarityScoreNormed : String? = null,
    @SerializedName("usedTraitsCount"   ) var usedTraitsCount   : String? = null,
    @SerializedName("rank"              ) var rank              : Int?    = null

)