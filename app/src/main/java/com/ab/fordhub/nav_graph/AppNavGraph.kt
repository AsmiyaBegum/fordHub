package com.ab.fordhub.nav_graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ab.fordhub.ford_centers.FordCenters
import com.ab.fordhub.ford_home.FordHomeScreen
import com.ab.fordhub.route.Root
import com.ab.fordhub.route.Route

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController , startDestination = Root.WELCOME, route = Root.APPROOT) {
        welcomeNavGraph()
        composable(Root.HOME){
            FordHomeScreen()
        }
//        homeNavGraph()
        testDriveGraph()
        serviceGraph()
    }
}