package com.android.rent4less.domain.models

import com.google.gson.annotations.SerializedName

data class Similar (

	@SerializedName("id") val id : Int?,
	@SerializedName("image") val image : List<Image?>?,
	@SerializedName("title") val title : String?,
	@SerializedName("slug") val slug : String?,
	@SerializedName("code") val code : String?,
	@SerializedName("address") val address : String?,
	@SerializedName("description") val description : String?,
	@SerializedName("purpose") val purpose : String?,
	@SerializedName("option") val option : String?,
	@SerializedName("is_shared") val is_shared : Boolean?,
	@SerializedName("available") val available : Boolean?,
	@SerializedName("featured") val featured : Boolean?,
	@SerializedName("status") val status : String?,
	@SerializedName("created_on") val created_on : String?,
	@SerializedName("updated_on") val updated_on : String?,
	@SerializedName("uploaded_by") val uploaded_by : Int?,
	@SerializedName("estate") val estate : Int?,
	@SerializedName("block") val block : Int?,
	@SerializedName("flat") val flat : Int?,
	@SerializedName("property_type") val property_type : Int?,
	@SerializedName("country") val country : Int?,
	@SerializedName("state") val state : Int?,
	@SerializedName("city") val city : Int?,
	@SerializedName("features") val features : List<Int?>?,
	@SerializedName("payment_duration") val payment_duration : List<Int?>?
)