package com.android.rent4less.domain.usecases

import com.android.rent4less.domain.repository.Repository
import javax.inject.Inject

class GetHomePageDataUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke() =
        repository.getHomePageData()
}