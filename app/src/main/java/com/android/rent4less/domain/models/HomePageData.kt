package com.android.rent4less.domain.models

import RecentProperties
import com.google.gson.annotations.SerializedName

data class HomePageData (
	@SerializedName("property_type") val property_type : List<PropertyType?>? = arrayListOf(),
	@SerializedName("newsletter_type") val newsletter_type : List<NewsletterType?>? = arrayListOf(),
	@SerializedName("location") val location : List<Location?>? = arrayListOf(),
	@SerializedName("featured_properties") val featured_properties : ArrayList<FeaturedProperties?>? = arrayListOf(),
	@SerializedName("upcoming_locations") val upcoming_locations : List<String?>? = arrayListOf(),
	@SerializedName("recent_properties") val recent_properties : RecentProperties? = null,
	@SerializedName("posts") val posts : Posts? = null,
	@SerializedName("testimonies") val testimonies : Testimonies? = null,
	@SerializedName("apartments") val apartments : Int? = 0,
	@SerializedName("bedrooms") val bedrooms : Int? = 0,
	@SerializedName("office_space") val office_space : Int? = 0,
	@SerializedName("customers") val customers : Int? = 0
)