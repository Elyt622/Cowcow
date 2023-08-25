package com.example.hellocowcow.data.retrofit.mvxApi.response

import com.google.gson.annotations.SerializedName


data class Assets (

    @SerializedName("website") var website : String? = null,
    @SerializedName("description") var description : String? = null,
    @SerializedName("social") var social : Social? = Social(),
    @SerializedName("ledgerSignature") var ledgerSignature : String? = null,
    @SerializedName("status") var status : String? = null,
    @SerializedName("extraTokens") var extraTokens : ArrayList<String> = arrayListOf(),
    @SerializedName("pngUrl") var pngUrl : String? = null,
    @SerializedName("svgUrl") var svgUrl : String? = null

)