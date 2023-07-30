package com.example.hellocowcow.domain.models

data class DomainAccount (
    var address         : String? = null,
    var balance         : String? = null,
    var nonce           : Int?    = null,
    var timestamp       : Int?    = null,
    var shard           : Int?    = null,
    var rootHash        : String? = null,
    var txCount         : Int?    = null,
    var scrCount        : Int?    = null,
    var username        : String? = null,
    var developerReward : String? = null
)