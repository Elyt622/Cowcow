package com.example.hellocowcow.data.response.mvxApi

import com.google.gson.annotations.SerializedName


data class MediaResponse (

    @SerializedName("url"          ) var url          : String? = null,
    @SerializedName("originalUrl"  ) var originalUrl  : String? = null,
    @SerializedName("thumbnailUrl" ) var thumbnailUrl : String? = null,
    @SerializedName("fileType"     ) var fileType     : String? = null,
    @SerializedName("fileSize"     ) var fileSize     : Int?    = null

)