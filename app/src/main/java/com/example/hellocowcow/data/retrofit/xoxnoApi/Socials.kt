package com.example.hellocowcow.data.retrofit.xoxnoApi

import com.google.gson.annotations.SerializedName

data class Socials (
    @SerializedName("website" ) var website : String? = null,
    @SerializedName("twitter" ) var twitter : String? = null,
    @SerializedName("discord" ) var discord : String? = null
)