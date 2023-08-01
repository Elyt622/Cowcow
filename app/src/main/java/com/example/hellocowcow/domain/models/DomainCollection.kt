package com.example.hellocowcow.domain.models

data class DomainCollection (
    var stakedCows : Int? = null,
    var holdersCows : Int? = null,
    var listedCows : Int? = null,
    var floorPriceCows : Double? = null,
    var totalUpgradedCows : Int? = null,
    var athEgldPrice: Double? = null,
    var totalTrades: Int? = null,
    var followAccounts: Int? = null,
    var dayEgldVolume: Double? = null,
    var weekEgldVolume: Double? = null,
    var totalEgldVolume: Double? = null
)