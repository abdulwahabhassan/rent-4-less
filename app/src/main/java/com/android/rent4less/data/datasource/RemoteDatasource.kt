package com.android.rent4less.data.datasource

import com.android.rent4less.domain.Result
import com.android.rent4less.domain.models.AccountResponse
import com.android.rent4less.domain.models.HomePageData

interface RemoteDatasource {
        suspend fun getRemoteHomePageData() : Result<HomePageData>
        suspend fun createNewAccount(name: String, username: String, password: String): Result<AccountResponse>
}