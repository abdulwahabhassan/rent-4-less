package com.android.rent4less.domain.models

import com.google.gson.annotations.SerializedName

data class Flat (

	@SerializedName("id") val id : Int?,
	@SerializedName("name") val name : String?,
	@SerializedName("code") val code : String?,
	@SerializedName("created_on") val created_on : String?,
	@SerializedName("updated_on") val updated_on : String?,
	@SerializedName("block") val block : Int?
)