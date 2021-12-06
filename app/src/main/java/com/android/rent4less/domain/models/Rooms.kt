package com.android.rent4less.domain.models

import com.google.gson.annotations.SerializedName

data class Rooms (

	@SerializedName("id") val id : Int?,
	@SerializedName("files") val files : List<String?>?,
	@SerializedName("total") val total : Int?,
	@SerializedName("transaction_fee") val transaction_fee : Int?,
	@SerializedName("name") val name : String?,
	@SerializedName("code") val code : String?,
	@SerializedName("type") val type : String?,
	@SerializedName("description") val description : String?,
	@SerializedName("sqm") val sqm : Int?,
	@SerializedName("unit") val unit : Int?,
	@SerializedName("bathroom") val bathroom : Int?,
	@SerializedName("toilet") val toilet : Int?,
	@SerializedName("amount") val amount : Double?,
	@SerializedName("service_charge") val service_charge : Double?,
	@SerializedName("caution_fee") val caution_fee : Double?,
	@SerializedName("electricity_fee") val electricity_fee : Double?,
	@SerializedName("other_fee") val other_fee : Double?,
	@SerializedName("available") val available : Boolean?,
	@SerializedName("featured") val featured : Boolean?,
	@SerializedName("created_on") val created_on : String?,
	@SerializedName("updated_on") val updated_on : String?,
	@SerializedName("property") val property : Int?
)