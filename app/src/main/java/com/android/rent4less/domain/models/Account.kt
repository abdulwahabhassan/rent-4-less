package com.android.rent4less.domain.models

import com.google.gson.annotations.SerializedName

data class Account (
    @SerializedName("first_name") val first_name: String,
    @SerializedName("last_name") val last_name: String = "",
    @SerializedName("email") val email: String = "",
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String,
    @SerializedName("phone_number") val phone_number: String = "",
    @SerializedName("country_id") val country_id: Int = 0,
    @SerializedName("registration_code") val registration_code: String = "",
    @SerializedName("referral") val referral: String = ""
        )