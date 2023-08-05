package com.example.hellocowcow.data.response.mvxApi

class TransactionRequest(
    var nonce    : Int    = 0,
    var value    : String = "0",
    var receiver : String = "",
    var sender   : String = "",
    var gasPrice : Long    = 0,
    var gasLimit : Long    = 0,
    var data     : String? = null,
    var chainID  : String = "1",
    var version  : Int    = 1,
    var signature : String = ""
)