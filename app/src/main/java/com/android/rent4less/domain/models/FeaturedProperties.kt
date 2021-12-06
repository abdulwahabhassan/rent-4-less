package com.android.rent4less.domain.models

import Estate
import com.google.gson.annotations.SerializedName

data class FeaturedProperties (

	@SerializedName("id") val id : Int?,
	@SerializedName("country") val country : Country?,
	@SerializedName("state") val state : State?,
	@SerializedName("city") val city : City?,
	@SerializedName("review") val review : String?,
	@SerializedName("average_rating") val average_rating : Int?,
	@SerializedName("property_type") val property_type : String?,
	@SerializedName("similar") val similar : List<Similar?>?,
	@SerializedName("payment_duration") val payment_duration : List<PaymentDuration?>?,
	@SerializedName("features") val features : List<Features?>?,
	@SerializedName("images") val images : List<Image?>?,
	@SerializedName("videos") val videos : String?,
	@SerializedName("other_files") val other_files : String?,
	@SerializedName("other_property") val other_property : String?,
	@SerializedName("rooms") val rooms : List<Rooms?>?,
	@SerializedName("details") val details : Details?,
	@SerializedName("amount") val amount : Int?,
	@SerializedName("total") val total : Int?,
	@SerializedName("transaction_fee") val transaction_fee : Int?,
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
	@SerializedName("estate") val estate : Estate?,
	@SerializedName("block") val block : Block?,
	@SerializedName("flat") val flat : Flat?
)