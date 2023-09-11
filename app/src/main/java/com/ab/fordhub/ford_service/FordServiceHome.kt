package com.ab.fordhub.ford_service

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavOptions
import com.ab.fordhub.R
import com.ab.fordhub.common.GradientButton
import com.ab.fordhub.common.VehicleServiceCarousel
import com.ab.fordhub.model.VehicleService
import com.ab.fordhub.model.VehicleServiceDetail
import com.ab.fordhub.route.Route
import com.ab.fordhub.ui.theme.darkIndigo
import com.ab.fordhub.ui.theme.midnightBlue
import com.ab.fordhub.ui.theme.royalBlue
import com.ab.fordnavigation.NavInfo


@Preview
@Composable
fun service(){
    FordServiceHome()
}

@Composable
fun FordServiceHome(
    viewModel: FordServiceViewModel = hiltViewModel()
) {
    val vehicleServices by viewModel.fordServices.observeAsState(emptyList())
    val bookedVehicleServices by viewModel.fordBookedServices.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.getVehicleServiceDetails()
        viewModel.getBookedVehicleServiceDetails()
    }

    val scrollState = rememberScrollState()

    Column {
        Spacer(modifier = Modifier.height(40.dp))
        VehicleServiceScreenContent(vehicleServices,bookedVehicleServices,scrollState,viewModel)

    }
}


@Composable
fun VehicleServiceScreenContent(vehicleService: List<VehicleService>,bookedVehicleService: List<VehicleServiceDetail>,scrollState: ScrollState,viewModel: FordServiceViewModel){
    Box(
        modifier = Modifier
            .fillMaxSize() // Fill the entire available space
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth() // Fill the width of the parent (the Box)
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState) // Enable vertical scrolling
        ) {
            VehicleServiceCarousel(
                list = vehicleService,
                carouselLabel = stringResource(R.string.vehicle_services),
                onItemClicked = { }
            )

            Spacer(modifier = Modifier.height(15.dp))

            if(bookedVehicleService.isEmpty()){
                GradientButton(text = "Book a Service", textColor = Color.White, gradient = Brush.linearGradient(
                    listOf(royalBlue, midnightBlue, darkIndigo)
                ), modifier = Modifier.align(Alignment.CenterHorizontally) ) {
                    viewModel.navManager.navigate(
                        NavInfo(id = Route.Home.Services.SERVICE_DETAIL,
                            navOption = NavOptions.Builder().setPopUpTo(Route.Home.Services.SERVICE_LIST, inclusive = true).build()
                        )
                    )
                }
            }else{
                Card(modifier = Modifier.height(400.dp), elevation = 10.dp, backgroundColor = Color.Transparent){
                    BookedServiceCard(bookedVehicleService)
                }



            }
        }
        if(bookedVehicleService.isNotEmpty()) {
            ExtendedFloatingActionButton(
                onClick = { },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                androidx.compose.material3.Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Guest list"
                )

            }
        }

    }
}


@Composable
fun BookedServiceCard(bookedVehicleService: List<VehicleServiceDetail>) {
    LazyColumn(
        modifier =  Modifier.fillMaxWidth()
            .padding(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        state = rememberLazyListState()
    ) {
        items(bookedVehicleService.size) { index ->
            bookedVehicleService[index]?.let {data ->
                 BookedVehicleServiceDetailCard(vehicleServiceDetail = data)
            }
        }
    }
}


@Composable
fun BookedVehicleServiceDetailCard(vehicleServiceDetail: VehicleServiceDetail){
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(25.dp)
    ) {
        val (car,cancelIcon,serviceType,date, location,statusTitle, status) = createRefs()

        Text(text = "${vehicleServiceDetail.carName}(${vehicleServiceDetail.model})",
            modifier = Modifier
                .constrainAs(car) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(cancelIcon.start)
                    width = Dimension.fillToConstraints
                }
                .padding(10.dp))

        Icon(painter = painterResource(id = R.drawable.ic_delete), contentDescription = "Service Cancel" ,
            modifier = Modifier
                .constrainAs(cancelIcon) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .padding(10.dp))


        Text(text = "Service : ${vehicleServiceDetail.serviceType}",
            modifier = Modifier
                .constrainAs(serviceType) {
                    top.linkTo(car.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .padding(10.dp))

        Text(text = "Date : ${vehicleServiceDetail.date}",
            modifier = Modifier
                .constrainAs(date) {
                    top.linkTo(serviceType.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .padding(10.dp))

        Text(text = "Location : ${vehicleServiceDetail.location}",
            modifier = Modifier
                .constrainAs(location) {
                    top.linkTo(date.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .padding(10.dp))

        Text(text = "Status : Booked",
            modifier = Modifier
                .constrainAs(statusTitle) {
                    top.linkTo(location.bottom)
                    start.linkTo(parent.start)
                }
                .padding(10.dp))

        Text(text = "Booked",
            modifier = Modifier
                .constrainAs(status) {
                    start.linkTo(statusTitle.end)
                    top.linkTo(location.bottom)
                }
                .padding(10.dp)
                .background(
                    Brush.linearGradient(listOf(royalBlue, midnightBlue, darkIndigo)),
                    shape = RoundedCornerShape(5.dp)
                )
                .then(Modifier.background(Color.White)), // Set the font color to white
            color = Color.White)

    }

}