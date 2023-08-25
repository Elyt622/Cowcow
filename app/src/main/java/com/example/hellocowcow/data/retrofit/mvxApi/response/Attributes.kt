package com.example.hellocowcow.data.retrofit.mvxApi.response

import com.google.gson.annotations.SerializedName


data class Attributes (

    @SerializedName("trait_type" ) var traitType : String? = null,
    @SerializedName("value"      ) var value     : String? = null

)