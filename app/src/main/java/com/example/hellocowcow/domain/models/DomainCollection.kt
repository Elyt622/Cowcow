package com.example.hellocowcow.domain.models

abstract class DomainCollection (
    open var holdersCount : Int? = null,
    open var listedCount : Int? = null,
    open var floorPrice : Double? = null,
    open var athEgldPrice: Double? = null,
    open var totalTrades: Int? = null,
    open var followAccountsCount: Int? = null,
    open var dayEgldVolume: Double? = null,
    open var weekEgldVolume: Double? = null,
    open var totalEgldVolume: Double? = null,
)

data class CowCollection (
    var stakedCount: Int?,
    override var holdersCount : Int? = null,
    override var listedCount : Int? = null,
    override var floorPrice : Double? = null,
    var totalUpgradedCount : Int? = null,
    override var athEgldPrice: Double? = null,
    override var totalTrades: Int? = null,
    override var followAccountsCount: Int? = null,
    override var dayEgldVolume: Double? = null,
    override var weekEgldVolume: Double? = null,
    override var totalEgldVolume: Double? = null
) : DomainCollection()

data class TicketCollection (
    override var holdersCount : Int? = null,
    override var listedCount : Int? = null,
    override var floorPrice : Double? = null,
    var ticketsUsed : Int? = null,
    override var athEgldPrice: Double? = null,
    override var totalTrades: Int? = null,
    override var followAccountsCount: Int? = null,
    override var dayEgldVolume: Double? = null,
    override var weekEgldVolume: Double? = null,
    override var totalEgldVolume: Double? = null
) : DomainCollection()