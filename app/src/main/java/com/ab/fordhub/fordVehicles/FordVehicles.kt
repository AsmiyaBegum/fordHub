package com.ab.fordhub.fordVehicles

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavOptions
import com.ab.fordhub.R
import com.ab.fordhub.common.ChipGroup
import com.ab.fordhub.common.HomeMediaCarousel
import com.ab.fordhub.common.HomeMediaRow
import com.ab.fordhub.common.SearchBox
import com.ab.fordhub.common.SearchWidgetState
import com.ab.fordhub.model.VehicleDetail
import com.ab.fordhub.route.Root
import com.ab.fordhub.route.Route
import com.ab.fordnavigation.NavInfo

@Composable
@Preview
fun fordVehiclesScreen(
    viewModel: FordVehiclesViewModel = hiltViewModel()
){
    val recentVehicles by viewModel.recentVehicles.observeAsState(emptyList())
    val testDriveVehicles by viewModel.testDriveVehicles.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.getRecentVehicles()
        viewModel.getTestDriveVehicles()
    }

    val scrollState = rememberScrollState()
    
    Column {
        Spacer(modifier = Modifier.height(40.dp))
        VehicleScreenContent(recentVehicles,testDriveVehicles,scrollState,viewModel){ vehicleDetail ->
            viewModel.navManager.navigate(
            NavInfo(id = "${Route.Home.TestDrive.VEHICLE_DETAIL}/$vehicleDetail",
                navOption = NavOptions.Builder().setPopUpTo(Route.Home.TestDrive.VEHICLE_LIST, inclusive = true).build()
            ))
        }

    }


}

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun VehicleScreenContent(
    recentVehicles: List<VehicleDetail>,
    testDriveVehicles: List<VehicleDetail>,
    scrollState: ScrollState,
    viewModel: FordVehiclesViewModel,
    onItemClick: (VehicleDetail) -> Unit,
    ) {
    val searchTextState by viewModel.searchTextState

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
            HomeMediaCarousel(
                list = recentVehicles,
                carouselLabel = stringResource(R.string.recent_vehicles),
                onItemClicked = { onItemClick(it) }
            )

            Spacer(modifier = Modifier.height(5.dp))
            SearchBox(
                text = searchTextState,
                onTextChange = { viewModel.updateSearchTextState(newValue = it) },
                onCloseClicked = { viewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED) },
                onSearchClicked = {
                    // searched text
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                ChipGroup() { it ->
                    Log.d("Selected", "Selected String - $it")
                }
            }

                HomeMediaRow(
                    title = stringResource(R.string.test_drive_vehicles),
                    list = testDriveVehicles,
                    onItemClicked = { onItemClick(it) }
                )
        }
    }
}
