package com.example.hellocowcow.data.retrofit.mvxApi.response

import com.example.hellocowcow.domain.DomainModelConvertible
import com.example.hellocowcow.domain.models.DomainOperation
import com.google.gson.annotations.SerializedName

data class Operations (

  @SerializedName("id"         ) var id         : String? = null,
  @SerializedName("action"     ) var action     : String? = null,
  @SerializedName("type"       ) var type       : String? = null,
  @SerializedName("esdtType"   ) var esdtType   : String? = null,
  @SerializedName("identifier" ) var identifier : String? = null,
  @SerializedName("name"       ) var name       : String? = null,
  @SerializedName("sender"     ) var sender     : String? = null,
  @SerializedName("receiver"   ) var receiver   : String? = null,
  @SerializedName("value"      ) var value      : String? = null,
  @SerializedName("decimals"   ) var decimals   : Int?    = null,
  @SerializedName("svgUrl"     ) var svgUrl     : String? = null,
  @SerializedName("data"       ) var data       : String? = null

) : DomainModelConvertible<DomainOperation> {

  override fun toDomain() : DomainOperation =
    DomainOperation(
      id         = id,
      action     = action,
      type       = type,
      esdtType   = esdtType,
      identifier = identifier,
      name       = name,
      sender     = sender,
      receiver   = receiver,
      value      = value,
      decimals   = decimals,
      svgUrl     = svgUrl,
      data       = data
    )

}
