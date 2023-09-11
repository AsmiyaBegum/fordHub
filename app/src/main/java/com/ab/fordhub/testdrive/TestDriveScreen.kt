package com.ab.fordhub.testdrive

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavOptions
import com.ab.fordhub.common.GradientButton
import com.ab.fordhub.common.MaterialDateDialog
import com.ab.fordhub.common.MaterialTimeDialog
import com.ab.fordhub.model.VehicleDetail
import com.ab.fordhub.route.Root
import com.ab.fordhub.route.Route
import com.ab.fordhub.ui.theme.darkIndigo
import com.ab.fordhub.ui.theme.midnightBlue
import com.ab.fordhub.ui.theme.royalBlue
import com.ab.fordnavigation.NavInfo
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TestDriveScreen(vehicleDetail: VehicleDetail,viewModel: TestDriveScreenViewModel = hiltViewModel()){
    Surface(modifier = Modifier.fillMaxSize()) {
        var pickedDate by remember {
            mutableStateOf(LocalDate.now())
        }
        var pickedTime by remember {
            mutableStateOf(LocalTime.NOON)
        }
        val formattedDate by remember {
            derivedStateOf {
                DateTimeFormatter
                    .ofPattern("MMM dd yyyy")
                    .format(pickedDate)
            }
        }
        val formattedTime by remember {
            derivedStateOf {
                DateTimeFormatter
                    .ofPattern("hh:mm")
                    .format(pickedTime)
            }
        }

        val dateDialogState = rememberMaterialDialogState()
        val timeDialogState = rememberMaterialDialogState()

        val location = listOf(
            "Mumbai",
            "Delhi",
            "Bangalore",
            "Hyderabad",
            "Chennai",
            "Kolkata",
            "Pune",
            "Ahmedabad",
            "Jaipur",
            "Lucknow",
            "Kanpur",
            "Nagpur",
            "Indore",
            "Thane",
            "Bhopal",
            "Visakhapatnam",
        )
        val center = listOf(
            "Sadar Bazaar",
            "Rajouri Garden",
            "Khan Market",
            "Connaught Place",
            "Chandni Chowk",
            "Karol Bagh",
            "Lajpat Nagar",
            "Hauz Khas",
            "Dwarka",
            "South Extension",
            "Vasant Kunj",
            "Greater Kailash",
            "Malviya Nagar",
            "Kamla Nagar",
            "Paharganj",
            "Nehru Place",
            "Saket",
            "Chanakyapuri",
            "Janakpuri",
            "New Friends Colony"
        )
        val context = LocalContext.current
        var locationText : String = ""
        var centerText : String = ""
        Column{
            Text(text = vehicleDetail.name, modifier = Modifier.padding(start = 30.dp,top = 30.dp) ,fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium)
            AutoComplete("Location",categories = location){
                locationText = it
            }
            Spacer(modifier = Modifier.height(5.dp))
            AutoComplete("Center",categories = center){
                centerText = it
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Slot Time", modifier = Modifier.padding(start = 30.dp,top = 30.dp) ,fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 30.dp),
                    elevation = 15.dp,
                    shape = RoundedCornerShape(10.dp),
                    onClick = { dateDialogState.show()}
                ){
                    Text(text = formattedDate, fontSize = 16.sp, modifier =  Modifier.padding(15.dp))
                }
                Card(
                    modifier = Modifier
                        .padding(horizontal = 15.dp),
                    elevation = 15.dp,
                    shape = RoundedCornerShape(10.dp),
                    onClick = { timeDialogState.show()}
                ){
                    Text(text = formattedTime, fontSize = 16.sp, modifier =  Modifier.padding(15.dp))
                }

                MaterialDateDialog(dateDialogState = dateDialogState, pickedDate = {pickedDate = it})
                MaterialTimeDialog(timeDialogState =  timeDialogState, pickedTime = {pickedTime = it})
            }


            GradientButton(text = "Book test drive", textColor = Color.White, gradient = Brush.horizontalGradient(
                listOf(midnightBlue, royalBlue, darkIndigo)) , modifier = Modifier
                .padding(10.dp)
                .align(CenterHorizontally)) {
                showMessage(context,"Booking made successfully")
                viewModel.navManager.navigate(
                    NavInfo(id = Root.HOME)
                )
            }


        }



        
    }
}

fun showMessage(context: Context, message:String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Composable
fun AutoComplete(textFieldName : String, categories : List<String>, categoryState  : (String) -> Unit) {

    var category by remember {
        mutableStateOf("")
    }

    val heightTextFields by remember {
        mutableStateOf(55.dp)
    }

    var textFieldSize by remember {
        mutableStateOf(Size.Zero)
    }

    var expanded by remember {
        mutableStateOf(false)
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }

    // Category Field
    Column(
        modifier = Modifier
            .padding(start = 30.dp, end = 30.dp, top = 10.dp)
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    expanded = false
                }
            )
    ) {

        Text(
            modifier = Modifier.padding(start = 3.dp, bottom = 3.dp),
            text = textFieldName,
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.Medium
        )

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp)) {

            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(heightTextFields)
                        .border(
                            width = 1.8.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(15.dp)
                        )
                        .onGloballyPositioned { coordinates ->
                            textFieldSize = coordinates.size.toSize()
                        },
                    value = category,
                    onValueChange = {
                        category = it
                        expanded = true
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.Black
                    ),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    singleLine = true,
                    trailingIcon = {
                        IconButton(onClick = { expanded = !expanded }) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                imageVector = Icons.Rounded.KeyboardArrowDown,
                                contentDescription = "arrow",
                                tint = Color.Black
                            )
                        }
                    }
                )
            }

            AnimatedVisibility(visible = expanded) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .width(textFieldSize.width.dp),
                    elevation = 15.dp,
                    shape = RoundedCornerShape(10.dp)
                ) {

                    LazyColumn(
                        modifier = Modifier.heightIn(max = 150.dp),
                    ) {

                        if (category.isNotEmpty()) {
                            items(
                                categories.filter {
                                    it.lowercase()
                                        .contains(category.lowercase()) || it.lowercase()
                                        .contains("others")
                                }
                                    .sorted()
                            ) {
                                CategoryItems(title = it) { title ->
                                    category = title
                                    expanded = false
                                }
                            }
                        } else {
                            items(
                                categories.sorted()
                            ) {
                                CategoryItems(title = it) { title ->
                                    category = title
                                    expanded = false
                                }
                            }
                        }

                    }

                }
            }

        }

    }

    LaunchedEffect(category){
        categoryState(category)
    }


}

@Composable
fun CategoryItems(
    title: String,
    onSelect: (String) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onSelect(title)
            }
            .padding(10.dp)
    ) {
        Text(text = title, fontSize = 16.sp)
    }

}