package com.example.hellocowcow.data.response.xoxnoApi

import com.google.gson.annotations.SerializedName

class StatisticsResponse (
    @SerializedName("tradeData" ) var tradeData : TradeDataResponse? = TradeDataResponse(),
    @SerializedName("mintData"  ) var mintData  : MintDataResponse?  = MintDataResponse(),
    @SerializedName("other"     ) var other     : OtherResponse?     = OtherResponse()
)