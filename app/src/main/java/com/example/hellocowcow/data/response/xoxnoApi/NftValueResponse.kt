package com.example.hellocowcow.data.response.xoxnoApi

import com.google.gson.annotations.SerializedName


data class NftValueResponse (

    @SerializedName("floorValue"   ) var floorValue   : Double? = null,
    @SerializedName("avgValue"     ) var avgValue     : Double? = null,
    @SerializedName("maxValue"     ) var maxValue     : Double?    = null,
    @SerializedName("collectionFp" ) var collectionFp : Double? = null

)