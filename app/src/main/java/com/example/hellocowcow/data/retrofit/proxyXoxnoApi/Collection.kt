package com.example.hellocowcow.data.retrofit.proxyXoxnoApi

import com.google.gson.annotations.SerializedName


data class Collection (

    @SerializedName("nftsWorth"           ) var nftsWorth           : Double?                        = null,
    @SerializedName("groupedByCollection" ) var groupedByCollection : ArrayList<GroupedByCollection> = arrayListOf()

)