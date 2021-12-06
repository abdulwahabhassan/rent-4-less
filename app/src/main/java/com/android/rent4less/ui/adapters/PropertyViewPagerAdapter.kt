package com.android.rent4less.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.rent4less.databinding.PropertyItemBinding
import com.android.rent4less.domain.models.FeaturedProperties
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.colley.android.glide.GlideImageLoader

class PropertyViewPagerAdapter(
    private val properties: ArrayList<FeaturedProperties?>,
    private val clickListener: ItemClickedListener,
    private val context: Context,
): RecyclerView.Adapter<PropertyViewPagerAdapter.PropertyViewPagerViewHolder>() {//Here we

inner class PropertyViewPagerViewHolder(private val itemBinding: PropertyItemBinding)
    : RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(property: FeaturedProperties, clickListener: ItemClickedListener, context: Context) =
        with(itemBinding) {
            itemBinding.dateAddedTextView.text = property.details?.created_on
            itemBinding.priceTextView.text = property.details?.amount.toString()
            itemBinding.roomsTextView.text = property.details?.bedroom.toString()
            itemBinding.showersTextView.text = property.details?.bathroom.toString()
            itemBinding.squareFeetTextView.text = property.details?.sqm.toString()
            itemBinding.tagTextView.text = "FOR ${property.purpose}"
            itemBinding.address.text = property.address
            itemBinding.titleTextView.text = property.title

            val options = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            GlideImageLoader(
                itemBinding.propertyPhotoImageView,
                itemBinding.photoProgressBar).load(property.images?.get(0)?.file, options)

        }
    }

    interface ItemClickedListener {
        fun onItemClick(user: FeaturedProperties)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewPagerViewHolder {
        val viewBinding = PropertyItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return PropertyViewPagerViewHolder(viewBinding)
    }

    //This method binds data to the created view
    override fun onBindViewHolder(holder: PropertyViewPagerViewHolder, position: Int) {
        val property = properties[position]
        if (property != null) {
            holder.bind(property, clickListener, context)
        }
    }

    //Gets size of our cards collection
    override fun getItemCount(): Int {
        return properties.size
    }

}