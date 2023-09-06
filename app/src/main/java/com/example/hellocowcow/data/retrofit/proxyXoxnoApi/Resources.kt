package com.example.hellocowcow.data.retrofit.proxyXoxnoApi

import com.google.gson.annotations.SerializedName


data class Resources (

    @SerializedName("txHash"         ) var txHash         : String?               = null,
    @SerializedName("collection"     ) var collection     : String?               = null,
    @SerializedName("identifier"     ) var identifier     : String?               = null,
    @SerializedName("timestamp"      ) var timestamp      : Int?                  = null,
    @SerializedName("action"         ) var action         : String?               = null,
    @SerializedName("price"          ) var price          : Double?               = null,
    @SerializedName("paymentToken"   ) var paymentToken   : String?               = null,
    @SerializedName("buyer"          ) var buyer          : String?               = null,
    @SerializedName("seller"         ) var seller         : String?               = null,
    @SerializedName("usdPrice"       ) var usdPrice       : Double?               = null,
    @SerializedName("egldValue"      ) var egldValue      : Double?               = null,
    @SerializedName("name"           ) var name           : String?               = null,
    @SerializedName("url"            ) var url            : String?               = null,
    @SerializedName("avifUrl"        ) var avifUrl        : String?               = null,
    @SerializedName("webpUrl"        ) var webpUrl        : String?               = null,
    @SerializedName("rank"           ) var rank           : Int?                  = null,
    @SerializedName("attributes"     ) var attributes     : ArrayList<Attributes> = arrayListOf(),
    @SerializedName("marketplace"    ) var marketplace    : String?               = null,
    @SerializedName("id"             ) var id             : String?               = null,
    @SerializedName("_ts"            ) var Ts             : Int?                  = null,
    @SerializedName("sellerUsername" ) var sellerUsername : String?               = null,
    @SerializedName("buyerUsername"  ) var buyerUsername  : String?               = null

)