package com.example.hellocowcow.datas.response.xoxnoApi

import com.google.gson.annotations.SerializedName


data class OriginalMediaResponse (

    @SerializedName("contentType"   ) var contentType   : String? = null,
    @SerializedName("contentLength" ) var contentLength : Int?    = null

)