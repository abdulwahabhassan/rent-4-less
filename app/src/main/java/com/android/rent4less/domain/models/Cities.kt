package com.android.rent4less.domain.models

import com.google.gson.annotations.SerializedName

data class Cities (

	@SerializedName("id") val id : Int?,
	@SerializedName("state_name") val state_name : String?,
	@SerializedName("name") val name : String?,
	@SerializedName("code") val code : String?,
	@SerializedName("image") val image : String?,
	@SerializedName("state") val state : Int?
)