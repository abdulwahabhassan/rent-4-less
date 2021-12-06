package com.android.rent4less

import com.android.rent4less.domain.models.HomePageData

object Utils {

    fun getSpinnerItems(homePageData: HomePageData?): ArrayList<String> {
        val types = arrayListOf<String>()
        if(homePageData != null) {
            homePageData.newsletter_type?.forEach { newLetterType ->
                newLetterType?.name?.let { name -> types.add(name) }
            }
        }
        return types
    }

    fun getLocations(homePageData: HomePageData?): ArrayList<String> {
        val locations = arrayListOf<String>()
        homePageData?.location?.forEach { location ->
            location?.name?.let { name -> locations.add(name) }
        }
        return locations
    }

    fun getPropertyTypes(homePageData: HomePageData?): ArrayList<String> {
        val propertyTypes = arrayListOf<String>()
        homePageData?.property_type?.forEach { propertyType ->
            propertyType?.name?.let { name -> propertyTypes.add(name)}
        }
        return propertyTypes
    }

    fun  validatePassword(passWord: String, confirmPassword: String) = passWord == confirmPassword

    fun validateDetails(name: String, userName: String, passwordResult: Boolean): Boolean {
        return name.isNotEmpty() && userName.isNotEmpty() && passwordResult
    }

}