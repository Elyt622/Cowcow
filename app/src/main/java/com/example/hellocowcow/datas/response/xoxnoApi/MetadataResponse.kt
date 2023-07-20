package com.example.hellocowcow.datas.response.xoxnoApi

import com.google.gson.annotations.SerializedName


data class MetadataResponse (

    @SerializedName("description" ) var description : String?               = null,
    @SerializedName("dna"         ) var dna         : String?               = null,
    @SerializedName("compiler"    ) var compiler    : String?               = null,
    @SerializedName("name"        ) var name        : String?               = null,
    @SerializedName("id"          ) var id          : String?               = null,
    @SerializedName("attributes"  ) var attributes  : ArrayList<AttributesResponse> = arrayListOf(),
    @SerializedName("rarity"      ) var rarity      : RarityResponse?       = RarityResponse()

)