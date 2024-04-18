package com.example.foodies.feature_foodies.presentation.Splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.foodies.R
import com.example.foodies.feature_foodies.presentation.navgraph.Route

@Composable
fun SplashScreen(
    navController: NavController
) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_screen_animation))
    val progress by animateLottieCompositionAsState(composition)

    LaunchedEffect(key1 = progress ){
        if (progress == 1f){
            navController.navigate(Route.Catalog.route){
                popUpTo(Route.SplashScreen.route){
                    inclusive = true
                }
            }
        }
    }

    LottieAnimation(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary),
        composition = composition,
        progress = { progress },
    )
}