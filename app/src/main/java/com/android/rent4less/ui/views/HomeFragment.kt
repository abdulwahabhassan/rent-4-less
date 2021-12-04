package com.android.rent4less.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.fragment.app.Fragment
import com.android.rent4less.R
import com.android.rent4less.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val mapOfSelections = mutableMapOf<String, String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.composeView.apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {

                MaterialTheme {

                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.SpaceEvenly) {

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
                                name = "locations",
                                defaultSymbol = "Location",
                                listOfItems = arrayListOf("Boy", "Girl", "Man", "Woman"),
                                onSymbolSelected = { name, selected ->  mapOfSelections[name] = selected }
                            )

                            DropDownEditText(
                                modifier = Modifier,
                                readOnly = false,
                                enabled = true,
                                name = "locations",
                                defaultSymbol = "Area",
                                listOfItems = arrayListOf("Boy", "Girl", "Man", "Woman"),
                                onSymbolSelected = { name, selected ->  mapOfSelections[name] = selected }
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
                                name = "locations",
                                defaultSymbol = "Type",
                                listOfItems = arrayListOf("Boy", "Girl", "Man", "Woman"),
                                onSymbolSelected = { name, selected ->  mapOfSelections[name] = selected }
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

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

@Composable
fun DropDownEditText(
    modifier: Modifier,
    readOnly: Boolean,
    enabled: Boolean,
    name: String,
    defaultSymbol: String,
    listOfItems: ArrayList<String>,
    onSymbolSelected: (String, String) -> Unit
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
            readOnly = readOnly,
            value = selectedSymbol,
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(fontSize = 13.sp),
            onValueChange = { newInput ->
                selectedSymbol = newInput
                onSymbolSelected(name, newInput)
            },
            trailingIcon = {
                Icon(
                    imageVector = if (isExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (isExpanded) "Show less" else "Show more",
                    Modifier
                        .clip(CircleShape)
                        .clickable(enabled = enabled) { isExpanded = !isExpanded },
                    tint = MaterialTheme.colors.onSecondary
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
                        onSymbolSelected(name, item)
                        isExpanded = false
                    }
                ) {
                    Text(text = item, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                }
            }
        }
    }
}