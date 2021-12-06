package com.android.rent4less.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.rent4less.domain.models.AccountResponse
import com.android.rent4less.domain.usecases.CreateNewAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val createNewAccountUseCase: CreateNewAccountUseCase
) : ViewModel() {

    private val _newAccount = MutableLiveData<AccountResponse?>()
    val newAccount = _newAccount

    suspend fun createNewAccount(name: String, username: String, password: String) {
        viewModelScope.launch {
            when (val result = createNewAccountUseCase.invoke(name, username, password)) {
                is com.android.rent4less.domain.Result.Success -> {
                    _newAccount.postValue(result.data)
                }
            }
        }
    }
}