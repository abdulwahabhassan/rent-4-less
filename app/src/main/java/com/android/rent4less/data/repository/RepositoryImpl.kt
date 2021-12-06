package com.android.rent4less.data.repository

import com.android.rent4less.data.datasource.RemoteDatasource
import com.android.rent4less.domain.Result
import com.android.rent4less.domain.models.AccountResponse
import com.android.rent4less.domain.models.HomePageData
import com.android.rent4less.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor  (
    private val remoteDatasource: RemoteDatasource
        ) : Repository {
    override suspend fun getHomePageData(): Result<HomePageData> {
        return remoteDatasource.getRemoteHomePageData()
    }

    override suspend fun createNewAccount(name: String, username: String, password: String): Result<AccountResponse> {
        return remoteDatasource.createNewAccount(name, username, password)
    }
}