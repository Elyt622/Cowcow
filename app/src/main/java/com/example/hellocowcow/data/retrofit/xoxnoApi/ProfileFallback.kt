package com.example.hellocowcow.data.retrofit.xoxnoApi

import com.google.gson.annotations.SerializedName

data class ProfileFallback (
    @SerializedName("dataType"              ) var dataType              : String?           = null,
    @SerializedName("collection"            ) var collection            : String?           = null,
    @SerializedName("name"                  ) var name                  : String?           = null,
    @SerializedName("description"           ) var description           : String?           = null,
    @SerializedName("isVisible"             ) var isVisible             : Boolean?          = null,
    @SerializedName("isVerified"            ) var isVerified            : Boolean?          = null,
    @SerializedName("profile"               ) var profile               : String?           = null,
    @SerializedName("banner"                ) var banner                : String?           = null,
    @SerializedName("statistics"            ) var statistics            : Statistics?       = Statistics(),
    @SerializedName("owner"                 ) var owner                 : String?           = null,
    @SerializedName("creator"               ) var creator               : String?           = null,
    @SerializedName("isMintable"            ) var isMintable            : Boolean?          = null,
    @SerializedName("hasStaking"            ) var hasStaking            : Boolean?          = null,
    @SerializedName("id"                    ) var id                    : String?           = null,
    @SerializedName("socials"               ) var socials               : Socials?          = Socials(),
    @SerializedName("type"                  ) var type                  : String?           = null,
    @SerializedName("lastVerifiedTimestamp" ) var lastVerifiedTimestamp : Int?              = null,
    @SerializedName("lastVerifiedBy"        ) var lastVerifiedBy        : String?           = null,
    @SerializedName("_ts"                   ) var Ts                    : Int?              = null
)