package com.example.hellocowcow.domain.models

data class DomainCollection (
    var stakedCount : Int? = null,
    var holdersCount : Int? = null,
    var listedCount : Int? = null,
    var floorPrice : Double? = null,
    var totalUpgradedCount : Int? = null,
    var athEgldPrice: Double? = null,
    var totalTrades: Int? = null,
    var followAccountsCount: Int? = null,
    var dayEgldVolume: Double? = null,
    var weekEgldVolume: Double? = null,
    var totalEgldVolume: Double? = null
)