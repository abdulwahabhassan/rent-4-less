package com.android.rent4less.domain.models

import com.google.gson.annotations.SerializedName


data class Details (

	@SerializedName("id") val id : Int?,
	@SerializedName("bedroom") val bedroom : Int?,
	@SerializedName("bathroom") val bathroom : Int?,
	@SerializedName("toilet") val toilet : Int?,
	@SerializedName("sqm") val sqm : Int?,
	@SerializedName("unit") val unit : Int?,
	@SerializedName("amount") val amount : Double?,
	@SerializedName("service_charge") val service_charge : Double?,
	@SerializedName("caution_fee") val caution_fee : Double?,
	@SerializedName("electricity_fee") val electricity_fee : Double?,
	@SerializedName("other_fee") val other_fee : Double?,
	@SerializedName("created_on") val created_on : String?,
	@SerializedName("updated_on") val updated_on : String?,
	@SerializedName("property") val property : Int?
)