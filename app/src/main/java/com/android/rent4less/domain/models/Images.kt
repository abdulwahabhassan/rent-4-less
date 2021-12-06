package com.android.rent4less.domain.models

import com.google.gson.annotations.SerializedName

data class Images (

	@SerializedName("id") val id : Int?,
	@SerializedName("image") val image : String?,
	@SerializedName("primary") val primary : Boolean?,
	@SerializedName("created_on") val created_on : String?,
	@SerializedName("post") val post : Int?
)