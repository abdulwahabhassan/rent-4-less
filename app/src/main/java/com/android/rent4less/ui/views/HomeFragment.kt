package com.android.rent4less.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.android.rent4less.R
import com.android.rent4less.Utils
import com.android.rent4less.databinding.FragmentHomeBinding
import com.android.rent4less.domain.models.FeaturedProperties
import com.android.rent4less.domain.models.HomePageData
import com.android.rent4less.ui.adapters.PropertyPagerAdapter
import com.android.rent4less.viewmodels.MainViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment :
    Fragment(), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var viewPagerFeatured: ViewPager2
    private lateinit var viewPagerAvailable: ViewPager2
    private lateinit var pagerAdapterFeatured: PropertyPagerAdapter
    private lateinit var pagerAdapterAvailable: PropertyPagerAdapter
    private lateinit var spinner: Spinner


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewPagerFeatured = binding.featuredPropertyViewPager
        viewPagerAvailable = binding.availableApartmentsViewPager

        spinner = binding.subscriptionSpinner

        binding.composeView.apply {

            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {

                MaterialTheme {

                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.SpaceEvenly) {

                        viewLifecycleOwner.lifecycleScope.launch {
                            viewModel.getHomePageData()
                        }

                        var locations by rememberSaveable { mutableStateOf(arrayListOf<String>())}
                        var propertyTypes by rememberSaveable { mutableStateOf(arrayListOf<String>())}
                        val area by rememberSaveable { mutableStateOf(arrayListOf<String>())}
                        var selectedLocation by rememberSaveable { mutableStateOf("")}
                        var selectedArea by rememberSaveable { mutableStateOf("")}
                        var selectedProperty by rememberSaveable { mutableStateOf("")}

                        val homePageData by viewModel.homePageData.observeAsState(HomePageData())

                        Row(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround) {

                            DropDownEditText(
                                modifier = Modifier,
                                readOnly = false,
                                enabled = true,
                                name = "Locations",
                                defaultSymbol = "Location",
                                listOfItems = locations,
                                homePageData = homePageData,
                                onSymbolSelected = { selected ->
                                    selectedLocation = selected
                                    if (selectedLocation != "Locations") {
                                        for (location in homePageData?.location!!) {
                                            if(location?.name == selected) {
                                                location.cities?.forEach { city ->
                                                    city?.name?.let { area.add(it) }
                                                }
                                            }
                                        }
                                    } else {
                                        area.clear()
                                    }
                                },
                                onDropDownClicked = {
                                    locations = Utils.getLocations(homePageData)
                                }
                            )

                            DropDownEditText(
                                modifier = Modifier,
                                readOnly = false,
                                enabled = true,
                                name = "Area",
                                defaultSymbol = "Area",
                                listOfItems = area,
                                homePageData = homePageData,
                                onSymbolSelected = { selected ->  selectedArea = selected },
                                onDropDownClicked = { }
                            )
                        }

                        Row(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround) {

                            DropDownEditText(
                                modifier = Modifier,
                                readOnly = false,
                                enabled = true,
                                name = "Property Type",
                                defaultSymbol = "Property",
                                listOfItems = propertyTypes,
                                homePageData = homePageData,
                                onSymbolSelected = { selected ->  selectedProperty = selected },
                                onDropDownClicked = { homePageData ->
                                    propertyTypes = Utils.getPropertyTypes(homePageData)
                                }
                            )

                            Button(
                                modifier = Modifier
                                    .width(120.dp)
                                    .height(50.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = colorResource(id = R.color.green),
                                    contentColor = colorResource(id = R.color.white)),
                                onClick = {  },
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(text = "SEARCH")
                            }
                        }
                    }
                }
            }
        }
        return binding.root
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinner.onItemSelectedListener = this

        viewModel.homePageData.observe(viewLifecycleOwner, { homePageData ->

            binding.apartmentCountTextView.text = homePageData?.apartments.toString()
            binding.happyCustomerCountTextView.text = homePageData?.customers.toString()
            binding.roomCountTextView.text = homePageData?.bedrooms.toString()
            binding.officeCountTextView.text = homePageData?.office_space.toString()

            val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                Utils.getSpinnerItems(homePageData)
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter

            val compositePageTransformer = CompositePageTransformer()
            compositePageTransformer.addTransformer(MarginPageTransformer(40))
            compositePageTransformer.addTransformer { page, position ->
                val r = 1 - Math.abs(position)
                page.scaleY = 0.85f + r * 0.15f
            }

            pagerAdapterFeatured = homePageData?.featured_properties?.let {
                PropertyPagerAdapter(it, childFragmentManager, viewLifecycleOwner.lifecycle)
            }!!
            viewPagerFeatured.adapter = pagerAdapterFeatured
            viewPagerFeatured.offscreenPageLimit = 3
            viewPagerFeatured.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            viewPagerFeatured.setPageTransformer(compositePageTransformer)


            pagerAdapterAvailable = PropertyPagerAdapter(
                homePageData.featured_properties,
                childFragmentManager,
                viewLifecycleOwner.lifecycle)

            viewPagerAvailable.adapter = pagerAdapterAvailable
            viewPagerAvailable.offscreenPageLimit = 3
            viewPagerAvailable.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            viewPagerAvailable.setPageTransformer(compositePageTransformer)
        })

        binding.nextImageButton.setOnClickListener {
            viewPagerAvailable.currentItem = viewPagerAvailable.currentItem + 1
        }

        binding.previousImageButton.setOnClickListener {
            viewPagerAvailable.currentItem = viewPagerAvailable.currentItem - 1
        }

        binding.nextPropertyImageButton.setOnClickListener {
            viewPagerFeatured.currentItem = viewPagerFeatured.currentItem + 1
        }

        binding.prevPropertyImageButton.setOnClickListener {
            viewPagerFeatured.currentItem = viewPagerFeatured.currentItem - 1
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {}

    override fun onNothingSelected(parent: AdapterView<*>?) {}

}

@Composable
fun DropDownEditText(
    modifier: Modifier,
    readOnly: Boolean,
    enabled: Boolean,
    name: String,
    defaultSymbol: String,
    listOfItems: ArrayList<String>,
    homePageData: HomePageData?,
    onSymbolSelected: (String) -> Unit,
    onDropDownClicked: (HomePageData) -> Unit
) {

    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    var selectedSymbol by rememberSaveable { mutableStateOf(defaultSymbol) }

    Box {

        OutlinedTextField(
            modifier = modifier
                .background(
                    shape = RoundedCornerShape(8.dp),
                    color = colorResource(id = R.color.spinner_gray)
                )
                .width(120.dp)
                .height(50.dp)
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            enabled = false,
            readOnly = readOnly,
            value = selectedSymbol,
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(fontSize = 13.sp),
            onValueChange = { newInput ->
                selectedSymbol = newInput
                onSymbolSelected(newInput)
            },
            trailingIcon = {
                Icon(
                    imageVector = if (isExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (isExpanded) "Show less" else "Show more",
                    Modifier
                        .clip(CircleShape)
                        .clickable(enabled = enabled) {
                            isExpanded = !isExpanded
                            if (name == "Locations") {
                                if (homePageData != null) {
                                onDropDownClicked(homePageData)
                                }
                            } else if (name == "Area") {
                                if (homePageData != null) {
                                    onDropDownClicked(homePageData)
                                }
                            } else if (name == "Property Type") {
                                if (homePageData != null) {
                                    onDropDownClicked(homePageData)
                                }
                            }
                                                      },
                    tint = colorResource(id = R.color.darkGray)
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                backgroundColor = colorResource(id = R.color.spinner_gray),
                cursorColor = MaterialTheme.colors.onSecondary,
                textColor = colorResource(id = R.color.darkGray)
            )
        )

        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                .height(200.dp)
        ) {
            listOfItems.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        selectedSymbol = item
                        onSymbolSelected(item)
                        isExpanded = false
                    }
                ) {
                    Text(
                        text = item,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = colorResource(id = R.color.darkGray)
                    )
                }
            }
        }
    }
}