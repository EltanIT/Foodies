package com.example.foodies.feature_foodies.presentation.navgraph

sealed class Route(
    val route: String
) {

    object Catalog: Route("Catalog")

    object ProductDetails: Route("ProductDetails?id={id}")

    object Card: Route("Card")

    object SplashScreen: Route("SplashScreen")


    object AppNavGraph: Route("MainNavGraph")
}