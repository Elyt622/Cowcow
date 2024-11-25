package com.example.hellocowcow.data.retrofit.mvxApi.request


data class Reward (
  var scAddress : String? = null,
  var funcName : String? = null,
  var value : String? = null,
  var args : ArrayList<String> = arrayListOf(),
  var caller : String? = null
)