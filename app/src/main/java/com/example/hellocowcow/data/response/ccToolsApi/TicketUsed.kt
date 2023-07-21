package com.example.hellocowcow.data.response.ccToolsApi

import com.google.gson.annotations.SerializedName


data class TicketUsed (

    @SerializedName("Chapter4"   ) var Chapter4   : Int?    = null,
    @SerializedName("Chapter4FP" ) var Chapter4FP : Double? = null

)