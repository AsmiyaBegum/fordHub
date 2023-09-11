package com.ab.fordhub.ford_home

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ab.fordhub.R
import com.ab.fordhub.fordVehicles.fordVehiclesScreen
import com.ab.fordhub.ford_centers.fordCenters
import com.ab.fordhub.ford_service.FordServiceHome
import com.ab.fordhub.ford_service.service
import com.ab.fordhub.model.ColorButton
import com.ab.fordhub.model.colorButtons
import com.ab.fordhub.profile.ProfileScreen
import com.ab.fordhub.route.Root
import com.ab.fordhub.route.Route
import com.ab.fordhub.ui.theme.darkIndigo
import com.ab.fordnavigation.NavInfo
import com.ab.fordnavigation.NavManager
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Straight
import com.exyte.animatednavbar.animation.indendshape.StraightIndent
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius

@Preview
@Composable
fun FordHomeScreen() {
    var selectedItem by remember { mutableStateOf(0) }
    var prevSelectedIndex by remember { mutableStateOf(0) }
    val navController = rememberNavController()


    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier
            ) {
                Column(
                    modifier = Modifier.align(Alignment.BottomCenter)
                ) {
//            updateUI(selectedItem, viewModel = hiltViewModel())
                    AnimatedNavigationBar(
                        modifier = Modifier
                            .height(55.dp)
                        ,
                        barColor = Color(0xFF4FB6D5),
                        selectedIndex = selectedItem,
                        ballColor = Color(0xFF4FB6D5),
                        ballAnimation = Straight(
                            spring(dampingRatio = 0.6f, stiffness = Spring.StiffnessVeryLow)
                        ),
                        indentAnimation = StraightIndent(
                            indentWidth = 56.dp,
                            indentHeight = 15.dp,
                            animationSpec = tween(1000)
                        )
                    ) {
                        colorButtons.forEachIndexed { index, it ->
                            ColorButton(
                                modifier = Modifier.fillMaxSize(),
                                prevSelectedIndex = prevSelectedIndex,
                                selectedIndex = selectedItem,
                                index = index,
                                onClick = {
                                    prevSelectedIndex = selectedItem
                                    selectedItem = index
                                    navController.navigate(it.route)
                                },
                                icon = it.icon,
                                contentDescription = stringResource(id = it.description),
                                animationType = it.animationType,
                                background = it.animationType.background,
                            )
                        }
                    }

                }
            }
        }) { paddingValues ->
        BottomNavGraph(navHostController = navController, paddingValues = paddingValues)
    }
}

@Composable
fun BottomNavGraph(navHostController: NavHostController,paddingValues : PaddingValues) {
    NavHost(
        navController = navHostController,
        startDestination = BottomNavItem.Home.screen_route,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = paddingValues.calculateBottomPadding())
            .background(Color.White)// Use appropriate modifier
    ) {
        composable(BottomNavItem.Home.screen_route) {
            fordVehiclesScreen()
        }
        composable(BottomNavItem.Service.screen_route) {
            service()
        }
        composable(BottomNavItem.Centre.screen_route) {
            fordCenters()
        }
        composable(BottomNavItem.Profile.screen_route) {
            ProfileScreen()
        }
    }
}

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){

    object Home : BottomNavItem("Home", R.drawable.user_login,Route.Home.TestDrive.VEHICLE_LIST)
    object Service: BottomNavItem("My Network",R.drawable.user_login,Route.Home.Services.SERVICE_LIST)
    object Centre: BottomNavItem("Post",R.drawable.user_login,Route.Home.CENTERS)
    object Profile: BottomNavItem("Notification",R.drawable.user_login,Route.Home.PROFILE)
}

@Composable
fun updateUI(selectedItem : Int,viewModel: FordHomeViewModel){
    when(selectedItem){
        0 -> viewModel.navManager.navigate(NavInfo(Root.Home.TESTDRIVE))
        1 -> viewModel.navManager.navigate(NavInfo(Root.Home.SERVICES))
        2 -> viewModel.navManager.navigate(NavInfo(Route.Home.CENTERS))
        3 -> viewModel.navManager.navigate(NavInfo(Route.Home.PROFILE))
    }
}



