package com.ab.fordhub.ford_service

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ab.fordhub.common.GradientButton
import com.ab.fordhub.common.MaterialDateDialog
import com.ab.fordhub.fordVehicles.FordVehiclesViewModel
import com.ab.fordhub.model.VehicleServiceDetail
import com.ab.fordhub.testdrive.AutoComplete
import com.ab.fordhub.ui.theme.darkIndigo
import com.ab.fordhub.ui.theme.midnightBlue
import com.ab.fordhub.ui.theme.royalBlue
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Preview
@Composable
fun addService(){
    AddFordVehicleService()
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddFordVehicleService(viewModel: FordServiceViewModel = hiltViewModel()) {

    var carState : String = ""
    var modelState : String = ""
    var serviceState : String = ""
    var locationState : String = ""
    val context = LocalContext.current

    Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {

        val serviceDetails by viewModel.serviceDetail.observeAsState(emptyList())

        LaunchedEffect(Unit) {
            viewModel.serviceDetails()
        }


        val dateDialogState = rememberMaterialDialogState()
        var pickedDate by remember {
            mutableStateOf(LocalDate.now())
        }
        val formattedDate by remember {
            derivedStateOf {
                DateTimeFormatter
                    .ofPattern("MMM dd yyyy")
                    .format(pickedDate)
            }
        }

                Column {
                    AutoComplete("Car", categories = serviceDetails.map { it.carName }) { it ->
                        carState = it
                    }
                    AutoComplete("Model", categories = serviceDetails.flatMap { it.carModel } ) { it ->
                        modelState = it
                    }
                    AutoComplete("Services", categories = serviceDetails.flatMap { it.availableServices }.distinct()) { it ->
                        serviceState = it
                    }
                    AutoComplete("Location", categories = serviceDetails.flatMap { it.locations }.distinct()) { it ->
                        locationState = it
                    }

                    Text(
                        text = "Date",
                        modifier = Modifier.padding(start = 30.dp, top = 30.dp),
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 30.dp),
                            elevation = 15.dp,
                            shape = RoundedCornerShape(10.dp),
                            onClick = { dateDialogState.show() }
                        ) {
                            Text(
                                text = formattedDate,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(15.dp)
                            )
                        }

                        MaterialDateDialog(
                            dateDialogState = dateDialogState,
                            pickedDate = { pickedDate = it })
                    }
                    GradientButton(
                        text = "Book Service",
                        textColor = Color.White,
                        gradient = Brush.horizontalGradient(
                            listOf(
                                midnightBlue, royalBlue, darkIndigo
                            )
                        ),
                        modifier = Modifier
                            .padding(10.dp)
                            .align(CenterHorizontally)
                    ) {

                        viewModel.insertBookedVehicleServiceDetails(VehicleServiceDetail(carName = carState, model = modelState, serviceType = serviceState, date = "10/12/23", location = locationState))
                        Toast.makeText(context,"$carState,$modelState,$serviceState,$locationState",Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }


