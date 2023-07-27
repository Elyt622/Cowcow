package com.example.hellocowcow.domain.models

import com.example.hellocowcow.data.response.xoxnoApi.MetadataResponse
import com.example.hellocowcow.data.response.xoxnoApi.NftValueResponse
import com.example.hellocowcow.data.response.xoxnoApi.OffersInfoResponse
import com.example.hellocowcow.data.response.xoxnoApi.OriginalMediaResponse
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
    var wasProcessed         : Boolean?          = null,
    var id                   : String?           = null,
    var rid                  : String?           = null,
    var onSale               : Boolean?           = null,
    var lastPrice            : String?           = null,
    var lastToken            : String?           = null,
    var hasOffers            : String?           = null,
    var retries              : Int?              = null,
    var hasSecondNFT         : Boolean?          = null,
    var metadata             : MetadataResponse? = MetadataResponse(),
    var originalMedia        : OriginalMediaResponse? = OriginalMediaResponse(),
    var saleInfoNft          : SaleInfoNftResponse? = SaleInfoNftResponse(),
    var offersInfo           : ArrayList<OffersInfoResponse> = arrayListOf(),
    var owner                : String?           = null,
    var ownerUsername        : String?           = null,
    var isVerified           : Boolean?          = null,
    var collectionName       : String?           = null,
    var isVisible            : Boolean?          = null,
    var nftValue             : NftValueResponse? = NftValueResponse(),
    var ticker               : String?           = null,

)