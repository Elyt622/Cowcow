package com.example.hellocowcow.data.response.xoxnoApi

import com.google.gson.annotations.SerializedName

data class SocialsResponse (
    @SerializedName("website" ) var website : String? = null,
    @SerializedName("twitter" ) var twitter : String? = null,
    @SerializedName("discord" ) var discord : String? = null
)