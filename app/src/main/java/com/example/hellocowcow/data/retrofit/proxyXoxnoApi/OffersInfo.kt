package com.example.hellocowcow.data.retrofit.proxyXoxnoApi

import com.google.gson.annotations.SerializedName


data class OffersInfo (

    @SerializedName("offer_id"      ) var offerId       : Int?     = null,
    @SerializedName("payment_token" ) var paymentToken  : String?  = null,
    @SerializedName("payment_nonce" ) var paymentNonce  : Int?     = null,
    @SerializedName("price"         ) var price         : String?  = null,
    @SerializedName("price_short"   ) var priceShort    : Double?  = null,
    @SerializedName("deadline"      ) var deadline      : Int?     = null,
    @SerializedName("timestamp"     ) var timestamp     : Int?     = null,
    @SerializedName("owner"         ) var owner         : String?  = null,
    @SerializedName("quantity"      ) var quantity      : Int?     = null,
    @SerializedName("EgldValue"     ) var EgldValue     : Double?  = null,
    @SerializedName("UsdValue"      ) var UsdValue      : Double?  = null,
    @SerializedName("ownerUsername" ) var ownerUsername : String?  = null,
    @SerializedName("isActive"      ) var isActive      : Boolean? = null

)