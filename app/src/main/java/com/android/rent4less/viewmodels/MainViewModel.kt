package com.android.rent4less.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.rent4less.domain.models.HomePageData
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

    suspend fun getHomePageData() {
        viewModelScope.launch {
            when (val result = getHomePageDataUseCase.invoke()) {
                is com.android.rent4less.domain.Result.Success -> {
                    _homePageData.postValue(result.data)
                }
            }
        }
    }


}