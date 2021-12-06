package com.android.rent4less.data.datasource

import android.util.Log
import com.android.rent4less.data.api.HomePageApi
import com.android.rent4less.data.api.SignUpApi
import com.android.rent4less.domain.Result
import com.android.rent4less.domain.models.AccountResponse
import com.android.rent4less.domain.models.HomePageData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDatasourceImpl @Inject constructor(
    private val homePageApi: HomePageApi,
    private val signUpApi: SignUpApi,
    private val dispatcherIO: CoroutineDispatcher
) : RemoteDatasource {

    override suspend fun getRemoteHomePageData(): Result<HomePageData> =
        withContext(dispatcherIO) {
            try {
                val response = homePageApi.getHomePageData()
                if (response.isSuccessful) {
                    return@withContext Result.Success(response.body()!!)
                } else {
                    return@withContext Result.Error(
                        Exception(response.message())
                    )
                }
            }catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }

    override suspend fun createNewAccount(name: String, username: String, password: String): Result<AccountResponse> =
        withContext(dispatcherIO) {
            try {
                val response = signUpApi.createNewAccount(name, username, password)
                if (response.isSuccessful) {
                    Log.d("apix", "${response.body()}")
                    return@withContext Result.Success(response.body()!!)
                } else {
                    Log.d("apix", "${response.body()}")
                    return@withContext Result.Error(
                        Exception(response.message())
                    )
                }
            }catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }
}