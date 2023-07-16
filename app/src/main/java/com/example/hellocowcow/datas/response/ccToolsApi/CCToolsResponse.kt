package com.example.hellocowcow.datas.response.ccToolsApi

import com.google.gson.annotations.SerializedName


data class CCToolsResponse (

    @SerializedName("ticketUsed"             ) var ticketUsed             : TicketUsed? = TicketUsed(),
    @SerializedName("circulatingSupplyMoove" ) var circulatingSupplyMoove : Int?        = null,
    @SerializedName("totalUnclaimedMoove"    ) var totalUnclaimedMoove    : Double?     = null,
    @SerializedName("unbondingCows"          ) var unbondingCows          : Int?        = null,
    @SerializedName("stakedCows"             ) var stakedCows             : Int?        = null,
    @SerializedName("inWalletCows"           ) var inWalletCows           : Int?        = null,
    @SerializedName("holdersCows"            ) var holdersCows            : Int?        = null,
    @SerializedName("listedCows"             ) var listedCows             : Int?        = null,
    @SerializedName("floorPriceCows"         ) var floorPriceCows         : Double?     = null,
    @SerializedName("floorPriceTickets"      ) var floorPriceTickets      : Double?     = null,
    @SerializedName("onSaleTickets"          ) var onSaleTickets          : Int?        = null,
    @SerializedName("mooveJEXPrice"          ) var mooveJEXPrice          : Double?     = null,
    @SerializedName("mooveJEXPriceUnit"      ) var mooveJEXPriceUnit      : String?     = null,
    @SerializedName("mooveXechangePriceUSD"  ) var mooveXechangePriceUSD  : Double?     = null,
    @SerializedName("totalUpgradedCows"      ) var totalUpgradedCows      : Int?        = null,
    @SerializedName("date"                   ) var date                   : String?     = null,
    @SerializedName("totalGeneratedMoove"    ) var totalGeneratedMoove    : Double?     = null

)