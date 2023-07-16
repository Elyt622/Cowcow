package com.example.hellocowcow.datas.response.mvxApi

import com.google.gson.annotations.SerializedName


data class AttributesResponse (

    @SerializedName("trait_type" ) var traitType : String? = null,
    @SerializedName("value"      ) var value     : String? = null

)