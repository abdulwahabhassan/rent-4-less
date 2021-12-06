package com.android.rent4less.domain.repository

import com.android.rent4less.domain.Result
import com.android.rent4less.domain.models.AccountResponse
import com.android.rent4less.domain.models.HomePageData

interface Repository {
    suspend fun getHomePageData() : Result<HomePageData>
    suspend fun createNewAccount(name: String, username: String, password: String) : Result<AccountResponse>
}