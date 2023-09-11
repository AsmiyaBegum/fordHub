package com.ab.fordhub.nav_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.ab.fordhub.fordVehicles.VehicleScreenContent
import com.ab.fordhub.fordVehicles.fordVehiclesScreen
import com.ab.fordhub.ford_centers.FordCenters
import com.ab.fordhub.ford_service.AddFordVehicleService
import com.ab.fordhub.ford_service.FordServiceHome
import com.ab.fordhub.model.VehicleDetail
import com.ab.fordhub.model.VehicleDetailArgType
import com.ab.fordhub.profile.ProfileScreen
import com.ab.fordhub.route.Root
import com.ab.fordhub.route.Route
import com.ab.fordhub.testdrive.TestDriveScreen
import com.ab.fordhub.testdrive.Vehicle
import com.ab.fordhub.testdrive.VehicleDetailScreen
import com.google.gson.Gson

fun NavGraphBuilder.homeNavGraph() {

    navigation(startDestination = Root.Home.TESTDRIVE, route = Root.HOME) {

        testDriveGraph()
        serviceGraph()

        composable(Route.Home.CENTERS){
            FordCenters()
        }

        composable(Route.Home.PROFILE){
            ProfileScreen()
        }

    }
}


fun NavGraphBuilder.testDriveGraph() {
    navigation(startDestination = Route.Home.TestDrive.VEHICLE_LIST, route = Root.Home.TESTDRIVE) {

        composable(Route.Home.TestDrive.VEHICLE_LIST) {
            fordVehiclesScreen()
        }

       composable("${Route.Home.TestDrive.TESTDRIVE_BOOKING}/{vehicleDetail}",arguments = listOf(navArgument("vehicleDetail"){
           type = VehicleDetailArgType() })){
           val vehicleDetail = it.arguments?.getString("vehicleDetail")?.let { Gson().fromJson(it, VehicleDetail::class.java) }
           TestDriveScreen(vehicleDetail!!)
       }

       composable("${Route.Home.TestDrive.VEHICLE_DETAIL}/{vehicleDetail}",arguments = listOf(navArgument("vehicleDetail"){
           type = VehicleDetailArgType() })) {
           val vehicleDetail = it.arguments?.getString("vehicleDetail")?.let { Gson().fromJson(it, VehicleDetail::class.java) }
           VehicleDetailScreen(vehicleDetail!!)
       }

    }
}

fun NavGraphBuilder.serviceGraph(){
    navigation(startDestination = Route.Home.Services.SERVICE_LIST, route = Root.Home.SERVICES) {

        composable(Route.Home.Services.SERVICE_LIST) {
            FordServiceHome()
        }

        composable(Route.Home.Services.SERVICE_DETAIL){
            AddFordVehicleService()
        }

    }
}


