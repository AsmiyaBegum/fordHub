package com.ab.fordhub.nav_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ab.fordhub.route.Root
import com.ab.fordhub.route.Route
import com.ab.fordhub.user_register.UserRegisterScreen
import com.ab.fordhub.welcome.OnBoardingPager
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
fun NavGraphBuilder.welcomeNavGraph() {

    navigation(startDestination = Route.Welcome.SPLASH, route = Root.WELCOME) {

        composable(Route.Welcome.SPLASH) {
            OnBoardingPager()
        }

        composable(Route.Welcome.LOGIN) {
            UserRegisterScreen()

        }



    }
}