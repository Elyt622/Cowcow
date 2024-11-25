package com.example.hellocowcow.data.retrofit.mvxApi.response

import com.example.hellocowcow.domain.DomainModelConvertible
import com.example.hellocowcow.domain.models.DomainOperation
import com.example.hellocowcow.domain.models.DomainTransaction
import com.google.gson.annotations.SerializedName


data class Transactions (

  @SerializedName("txHash") var txHash : String? = null,
  @SerializedName("gasLimit") var gasLimit : Int? = null,
  @SerializedName("gasPrice") var gasPrice : Int? = null,
  @SerializedName("gasUsed") var gasUsed : Int? = null,
  @SerializedName("miniBlockHash") var miniBlockHash : String? = null,
  @SerializedName("nonce") var nonce : Int? = null,
  @SerializedName("receiver") var receiver : String? = null,
  @SerializedName("round") var round : Int? = null,
  @SerializedName("sender") var sender : String? = null,
  @SerializedName("signature") var signature : String? = null,
  @SerializedName("status") var status : String? = null,
  @SerializedName("value") var value : String? = null,
  @SerializedName("fee") var fee : String? = null,
  @SerializedName("timestamp") var timestamp : Int? = null,
  @SerializedName("data") var data : String? = null,
  @SerializedName("function") var function : String? = null,
  @SerializedName("action") var action : Action? = Action(),
  @SerializedName("type") var type : String? = null,
  @SerializedName("originalTxHash") var originalTxHash : String? = null,
  @SerializedName("price") var price : Double? = null,
  @SerializedName("operations") var operations : ArrayList<Operations>? = arrayListOf()

) : DomainModelConvertible<DomainTransaction> {

  override fun toDomain() : DomainTransaction {

    val domainOperations : ArrayList<DomainOperation> = arrayListOf()
    operations?.let {
      for (operation in it) {
        domainOperations.add(operation.toDomain())
      }
    }

    return DomainTransaction(
      txHash = txHash,
      receiver = receiver,
      sender = sender,
      value = value,
      fee = fee,
      timestamp = timestamp,
      data = data,
      function = function,
      action = action,
      type = type,
      originalTxHash = originalTxHash,
      price = price,
      operations = domainOperations,
      status = status.toString()
    )
  }
}