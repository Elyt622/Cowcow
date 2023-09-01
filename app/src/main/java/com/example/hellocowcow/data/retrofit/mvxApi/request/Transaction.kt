package com.example.hellocowcow.data.retrofit.mvxApi.request

data class Transaction(
    var nonce    : Int    = 0,
    var value    : String = "0",
    var receiver : String = "",
    var sender   : String = "",
    var gasPrice : Long    = 0,
    var gasLimit : Long    = 0,
    var data     : String? = null,
    var chainID  : String = "1",
    var version  : Int    = 1,
    var signature : String = "",
    var options : Int = 0,
    var guardian : String = "",
    var guardianSignature : String = ""
)