package com.example.hellocowcow.domain.models

import com.example.hellocowcow.data.response.xoxnoApi.MetadataResponse
import com.example.hellocowcow.data.response.xoxnoApi.OffersInfoResponse
import com.example.hellocowcow.data.response.xoxnoApi.SaleInfoNftResponse

data class DomainNft (

    var identifier           : String?           = null,
    var collection           : String?           = null,
    var timestamp            : Int?              = null,
    var attributes           : String?           = null,
    var name                 : String?           = null,
    var creator              : String?           = null,
    var royalties            : Int?              = null,
    var uris                 : ArrayList<String> = arrayListOf(),
    var url                  : String?           = null,
    var thumbnailUrl         : String?           = null,
    var tags                 : ArrayList<String> = arrayListOf(),
    var avifUrl              : String?           = null,
    var webpUrl              : String?           = null,
    var onSale               : Boolean?           = null,
    var lastPrice            : String?           = null,
    var lastToken            : String?           = null,
    var hasOffers            : String?           = null,
    var retries              : Int?              = null,
    var hasSecondNFT         : Boolean?          = false,
    var metadata             : MetadataResponse? = MetadataResponse(),
    var saleInfoNft          : SaleInfoNftResponse? = SaleInfoNftResponse(),
    var offersInfo           : ArrayList<OffersInfoResponse> = arrayListOf(),
    var owner                : String?           = null,
    var floorValue           : Double?           = null,
    var avgValue             : Double?           = null,
    var maxValue             : Double?           = null,
    var collectionFp         : Double?           = null,
    var ticker               : String?           = null

)