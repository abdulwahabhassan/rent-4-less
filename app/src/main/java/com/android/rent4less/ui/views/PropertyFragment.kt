package com.android.rent4less.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.rent4less.databinding.PropertyItemBinding
import com.android.rent4less.domain.models.FeaturedProperties
import com.android.rent4less.ui.glide.GlideImageLoader
import com.bumptech.glide.request.RequestOptions

class PropertyFragment (private val property: FeaturedProperties) : Fragment() {

    private var _binding: PropertyItemBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            dateAddedTextView.text = property.details?.created_on
            priceTextView.text = property.details?.amount.toString()
            roomsTextView.text = property.details?.bedroom.toString()
            showersTextView.text = property.details?.bathroom.toString()
            squareFeetTextView.text = property.details?.sqm.toString()
            tagTextView.text = "FOR ${property.purpose}"
            address.text = property.address
            titleTextView.text = property.title

            val options = RequestOptions()
                .diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.RESOURCE)
            GlideImageLoader(
                propertyPhotoImageView,
                photoProgressBar).load(property.images?.get(0)?.file, options)

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PropertyItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}