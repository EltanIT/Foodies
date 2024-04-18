package com.example.foodies.feature_foodies.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.foodies.feature_foodies.presentation.Splash.SplashScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun AppNavHost(
    navHostController: NavHostController,
    startDestination: String = Route.AppNavGraph.route
) {
    val systemUiController = rememberSystemUiController()

    NavHost(navHostController, startDestination){
        composable(Route.SplashScreen.route){
            SplashScreen(navController = navHostController)
        }
        appNavGraph(
            navHostController,
            Route.Catalog.route,
            systemUiController
        )
    }
}