package com.android.rent4less.ui.adapters

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.rent4less.domain.models.FeaturedProperties
import com.android.rent4less.ui.views.PropertyFragment

class PropertyPagerAdapter(
    properties: ArrayList<FeaturedProperties?>,
    childFragmentManager: FragmentManager, lifecycle: Lifecycle
) : FragmentStateAdapter(childFragmentManager, lifecycle) {

    private val fragments: ArrayList<PropertyFragment> = arrayListOf()

    init {
        properties.forEach { property ->
            property?.let { PropertyFragment(it) }?.let { fragments.add(it) }
        }
    }

    override fun createFragment(position: Int) = fragments[position]

    override fun getItemCount(): Int = fragments.size
}