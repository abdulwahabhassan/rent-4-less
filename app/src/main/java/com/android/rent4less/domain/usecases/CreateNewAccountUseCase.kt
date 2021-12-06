package com.android.rent4less.domain.usecases

import com.android.rent4less.domain.repository.Repository
import javax.inject.Inject

class CreateNewAccountUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(name: String, username: String, password: String) =
        repository.createNewAccount(name, username, password)
}