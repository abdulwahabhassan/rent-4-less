package com.android.rent4less.domain.models

import com.google.gson.annotations.SerializedName

data class Location (

	@SerializedName("id") val id : Int?,
	@SerializedName("cities") val cities : List<Cities?>?,
	@SerializedName("name") val name : String?,
	@SerializedName("code") val code : String?,
	@SerializedName("country") val country : Int?
)