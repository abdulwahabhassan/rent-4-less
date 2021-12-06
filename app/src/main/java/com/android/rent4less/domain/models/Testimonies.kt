package com.android.rent4less.domain.models

import com.google.gson.annotations.SerializedName

data class Testimonies (

	@SerializedName("count") val count : Int?,
	@SerializedName("next") val next : String?,
	@SerializedName("previous") val previous : String?,
	@SerializedName("results") val results : List<Results?>?
)