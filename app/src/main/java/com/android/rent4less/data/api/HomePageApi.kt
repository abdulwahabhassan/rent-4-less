package com.android.rent4less.data.api

import com.android.rent4less.domain.models.HomePageData
import retrofit2.Response
import retrofit2.http.GET

interface HomePageApi {

    @GET("/")
    suspend fun getHomePageData(): Response<HomePageData>
}