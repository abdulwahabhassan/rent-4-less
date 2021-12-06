package com.android.rent4less.domain.models

import com.google.gson.annotations.SerializedName

data class NewsletterType (

	@SerializedName("id") val id : Int?,
	@SerializedName("name") val name : String?
)