package com.android.rent4less.data.api

import com.android.rent4less.domain.models.Account
import com.android.rent4less.domain.models.AccountResponse
import retrofit2.Response
import retrofit2.http.POST

interface SignUpApi {

    @POST("/account/register")
    suspend fun createNewAccount(name: String, username: String, password: String): Response<AccountResponse>
}