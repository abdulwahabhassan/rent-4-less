package com.android.rent4less.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.android.rent4less.domain.models.AccountResponse
import com.android.rent4less.domain.models.HomePageData
import com.android.rent4less.domain.usecases.CreateNewAccountUseCase
import com.android.rent4less.domain.usecases.GetHomePageDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getHomePageDataUseCase: GetHomePageDataUseCase
) : ViewModel() {

    private val _homePageData = MutableLiveData<HomePageData?>()
    val homePageData = _homePageData

    private val _error = MutableLiveData<String>(null)
    val error: LiveData<String> = _error

    suspend fun getHomePageData() {
        viewModelScope.launch {
            when (val result = getHomePageDataUseCase.invoke()) {
                is com.android.rent4less.domain.Result.Success -> {
                    _homePageData.postValue(result.data)
                }
                is com.android.rent4less.domain.Result.Error -> {
                    _error.postValue(result.exception.message)
                }
            }
        }
    }


}