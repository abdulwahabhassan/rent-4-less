package com.android.rent4less.domain.models

import com.google.gson.annotations.SerializedName

data class PropertyType (

	@SerializedName("id") val id : Int?,
	@SerializedName("name") val name : String?,
	@SerializedName("status") val status : String?,
	@SerializedName("children") val children : String?,
	@SerializedName("created_on") val created_on : String?,
	@SerializedName("updated_on") val updated_on : String?
)