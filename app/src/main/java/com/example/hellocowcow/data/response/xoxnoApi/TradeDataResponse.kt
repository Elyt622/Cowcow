package com.example.hellocowcow.data.response.xoxnoApi

import com.google.gson.annotations.SerializedName

data class TradeDataResponse (
    @SerializedName("dayEgldVolume"    ) var dayEgldVolume    : Double? = null,
    @SerializedName("weekEgldVolume"   ) var weekEgldVolume   : Double? = null,
    @SerializedName("totalEgldVolume"  ) var totalEgldVolume  : Double? = null,
    @SerializedName("averageEgldPrice" ) var averageEgldPrice : Double? = null,
    @SerializedName("athEgldPrice"     ) var athEgldPrice     : Double? = null,
    @SerializedName("athTxHash"        ) var athTxHash        : String? = null,
    @SerializedName("totalTrades"      ) var totalTrades      : Int?    = null
)