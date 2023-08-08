package com.example.hellocowcow.data.response.proxyXoxnoApi

import com.google.gson.annotations.SerializedName


data class AttributesResponse (

    @SerializedName("trait_type" ) var traitType  : String? = null,
    @SerializedName("value"      ) var value      : String? = null,
    @SerializedName("occurance"  ) var occurance  : Int?    = null,
    @SerializedName("frequency"  ) var frequency  : Double? = null,
    @SerializedName("FloorPrice" ) var floorPrice : Double? = null,
    @SerializedName("OnSale"     ) var onSale     : Int?    = null

)