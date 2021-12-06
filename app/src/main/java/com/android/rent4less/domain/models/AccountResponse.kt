package com.android.rent4less.domain.models

import com.google.gson.annotations.SerializedName

class AccountResponse (
    @SerializedName("id") val id: Int = 0,
    @SerializedName("first_name") val first_name: String,
    @SerializedName("last_name") val last_name: String,
    @SerializedName("email") val email: String,
    @SerializedName("username") val username: String,
    @SerializedName("location") val location: String,
    @SerializedName("verification") val verification: String,
    @SerializedName("account_number") val account_number: String,
    @SerializedName("phone_number") val phone_number: String,
    @SerializedName("status") val status: String,
    @SerializedName("profile_picture") val profile_picture: String,
    @SerializedName("created_on") val created_on: String,
    @SerializedName("updated_on") val updated_on: String,
    @SerializedName("display_balance_in") val display_balance_in: Int
)