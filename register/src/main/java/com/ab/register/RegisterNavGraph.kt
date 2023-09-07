package com.ab.register

fun NavGraphBuilder.welcomeNavGraph() {

    navigation(startDestination = Route.Welcome.SPLASH, route = Root.WELCOME) {

        composable(Route.Welcome.SPLASH) {
            SplashScreen()
        }

        composable(Route.Welcome.FEATURE) {
            FeatureScreen()

        }
        composable(Route.Welcome.TNC) {
            TermsAndConditionScreen()

        }


    }
}