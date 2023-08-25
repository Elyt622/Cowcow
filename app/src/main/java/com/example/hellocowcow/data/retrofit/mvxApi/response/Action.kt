package com.example.hellocowcow.data.retrofit.mvxApi.response

import com.google.gson.annotations.SerializedName


data class Action (

    @SerializedName("category") var category : String? = null,
    @SerializedName("name") var name : String? = null,
    @SerializedName("description") var description : String? = null,
    @SerializedName("arguments") var arguments : Arguments? = Arguments()

)