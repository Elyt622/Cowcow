package com.example.hellocowcow.data.retrofit.mvxApi.response

import com.google.gson.annotations.SerializedName


data class Metadata (

    @SerializedName("description" ) var description : String?               = null,
    @SerializedName("attributes"  ) var attributes  : ArrayList<Attributes> = arrayListOf(),
    @SerializedName("compiler"    ) var compiler    : String?               = null

)
