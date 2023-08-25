package com.example.hellocowcow.data.retrofit.mvxApi.response

import com.google.gson.annotations.SerializedName


data class Media (

    @SerializedName("url"          ) var url          : String? = null,
    @SerializedName("originalUrl"  ) var originalUrl  : String? = null,
    @SerializedName("thumbnailUrl" ) var thumbnailUrl : String? = null,
    @SerializedName("fileType"     ) var fileType     : String? = null,
    @SerializedName("fileSize"     ) var fileSize     : Int?    = null

)