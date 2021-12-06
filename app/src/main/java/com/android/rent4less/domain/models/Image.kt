package com.android.rent4less.domain.models

import com.google.gson.annotations.SerializedName

data class Image (

	@SerializedName("id") val id : Int?,
	@SerializedName("file") val file : String?,
	@SerializedName("file_type") val file_type : String?,
	@SerializedName("primary") val primary : Boolean?,
	@SerializedName("created_on") val created_on : String?,
	@SerializedName("property") val property : Int?
)