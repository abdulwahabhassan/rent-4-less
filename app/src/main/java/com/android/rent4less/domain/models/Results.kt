package com.android.rent4less.domain.models

import com.google.gson.annotations.SerializedName

data class Results (

	@SerializedName("id") val id : Int?,
	@SerializedName("name") val name : String?,
	@SerializedName("title") val title : String?,
	@SerializedName("content") val content : String?,
	@SerializedName("job") val job : String?,
	@SerializedName("rating") val rating : Int?,
	@SerializedName("image") val image : String?,
	@SerializedName("created_on") val created_on : String?,
	@SerializedName("updated_on") val updated_on : String?
)