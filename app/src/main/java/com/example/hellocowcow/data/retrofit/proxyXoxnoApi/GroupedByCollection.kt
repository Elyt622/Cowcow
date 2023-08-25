package com.example.hellocowcow.data.retrofit.proxyXoxnoApi

import com.google.gson.annotations.SerializedName


data class GroupedByCollection (

    @SerializedName("name"       ) var name       : String?           = null,
    @SerializedName("type"       ) var type       : String?           = null,
    @SerializedName("image"      ) var image      : String?           = null,
    @SerializedName("ticker"     ) var ticker     : String?           = null,
    @SerializedName("isVerified" ) var isVerified : Boolean?          = null,
    @SerializedName("isVisible"  ) var isVisible  : Boolean?          = null,
    @SerializedName("nftsCount"  ) var nftsCount  : Int?              = null,
    @SerializedName("nfts"       ) var nfts       : ArrayList<Nft>   = arrayListOf(),
    @SerializedName("bids"       ) var bids       : ArrayList<String> = arrayListOf(),
    @SerializedName("floorWorth" ) var floorWorth : Double?           = null,
    @SerializedName("maxWorth"   ) var maxWorth   : Double?           = null,
    @SerializedName("floorPrice" ) var floorPrice : Double?           = null,
    @SerializedName("totalWorth" ) var totalWorth : Double?           = null

)