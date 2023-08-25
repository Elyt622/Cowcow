package com.example.hellocowcow.data.retrofit.proxyXoxnoApi

import com.google.gson.annotations.SerializedName


data class SaleInfoNft (

    @SerializedName("auction_id"                   ) var auctionId                 : Int?    = null,
    @SerializedName("seller"                       ) var seller                    : String? = null,
    @SerializedName("current_winner"               ) var currentWinner             : String? = null,
    @SerializedName("min_bid"                      ) var minBid                    : String? = null,
    @SerializedName("max_bid"                      ) var maxBid                    : String? = null,
    @SerializedName("current_bid"                  ) var currentBid                : String? = null,
    @SerializedName("start_time"                   ) var startTime                 : Int?    = null,
    @SerializedName("deadline"                     ) var deadline                  : Int?    = null,
    @SerializedName("accepted_payment_token"       ) var acceptedPaymentToken      : String? = null,
    @SerializedName("accepted_payment_token_nonce" ) var acceptedPaymentTokenNonce : Int?    = null,
    @SerializedName("auction_type"                 ) var auctionType               : String? = null,
    @SerializedName("timestamp"                    ) var timestamp                 : Int?    = null,
    @SerializedName("min_bid_short"                ) var minBidShort               : Double? = null,
    @SerializedName("max_bid_short"                ) var maxBidShort               : Double? = null,
    @SerializedName("current_bid_short"            ) var currentBidShort           : Int?    = null,
    @SerializedName("quantity"                     ) var quantity                  : String? = null,
    @SerializedName("marketplace"                  ) var marketplace               : String? = null,
    @SerializedName("usd"                          ) var usd                       : String? = null,
    @SerializedName("usd_max_bid"                  ) var usdMaxBid                 : String? = null

)