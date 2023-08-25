package com.example.hellocowcow.data.retrofit.proxyXoxnoApi

import com.google.gson.annotations.SerializedName


data class Metadata (

    @SerializedName("description" ) var description : String?               = null,
    @SerializedName("dna"         ) var dna         : String?               = null,
    @SerializedName("compiler"    ) var compiler    : String?               = null,
    @SerializedName("name"        ) var name        : String?               = null,
    @SerializedName("id"          ) var id          : String?               = null,
    @SerializedName("attributes"  ) var attributes  : ArrayList<Attributes> = arrayListOf(),
    @SerializedName("rarity"      ) var rarity      : Rarity?       = Rarity()

)