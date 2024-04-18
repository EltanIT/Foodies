package com.example.foodies.feature_foodies.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.foodies.feature_foodies.presentation.navgraph.AppNavHost
import com.example.foodies.feature_foodies.presentation.navgraph.Route
import com.example.foodies.feature_foodies.presentation.ui.theme.FoodiesTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            viewModel = hiltViewModel()

            FoodiesTheme(
                darkTheme = false,
                dynamicColor = false
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navHostController = rememberNavController()
                    AppNavHost(
                        navHostController = navHostController,
                        startDestination = Route.SplashScreen.route
                    )
                }
            }
        }
    }

}
