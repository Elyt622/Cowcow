package com.example.hellocowcow.data.retrofit.proxyXoxnoApi

import com.google.gson.annotations.SerializedName


data class Rarity (

    @SerializedName("avgRarity"         ) var avgRarity         : String? = null,
    @SerializedName("statRarity"        ) var statRarity        : String? = null,
    @SerializedName("rarityScore"       ) var rarityScore       : Double? = null,
    @SerializedName("rarityScoreNormed" ) var rarityScoreNormed : String? = null,
    @SerializedName("usedTraitsCount"   ) var usedTraitsCount   : String? = null,
    @SerializedName("rank"              ) var rank              : Int?    = null

)