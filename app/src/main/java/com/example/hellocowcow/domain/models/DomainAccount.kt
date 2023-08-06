package com.example.hellocowcow.domain.models

data class DomainAccount (
    var address : String = "",
    var balance : String? = null,
    var nonce : Int = 0,
    var timestamp : Int? = null,
    var shard : Int? = null,
    var rootHash : String? = null,
    var txCount : Int? = null,
    var scrCount : Int? = null,
    var username : String = "",
    var developerReward : String? = null
)