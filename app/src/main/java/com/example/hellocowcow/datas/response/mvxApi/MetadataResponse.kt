package com.example.hellocowcow.datas.response.mvxApi

import com.google.gson.annotations.SerializedName


data class MetadataResponse (

    @SerializedName("description" ) var description : String?               = null,
    @SerializedName("attributes"  ) var attributes  : ArrayList<AttributesResponse> = arrayListOf(),
    @SerializedName("compiler"    ) var compiler    : String?               = null

)
