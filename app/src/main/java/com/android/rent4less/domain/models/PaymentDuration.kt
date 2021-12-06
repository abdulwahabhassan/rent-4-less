package com.android.rent4less.domain.models

import com.google.gson.annotations.SerializedName

data class PaymentDuration (

	@SerializedName("id") val id : Int?,
	@SerializedName("duration_name") val duration_name : String?,
	@SerializedName("number_of_months") val number_of_months : Int?,
	@SerializedName("number_of_days") val number_of_days : Int?
)