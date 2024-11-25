package com.example.hellocowcow.data.retrofit.xoxnoApi

import com.google.gson.annotations.SerializedName

class Statistics(
  @SerializedName("tradeData") var tradeData: TradeData? = TradeData(),
  @SerializedName("mintData") var mintData: MintData? = MintData(),
  @SerializedName("other") var other: Other? = Other()
)