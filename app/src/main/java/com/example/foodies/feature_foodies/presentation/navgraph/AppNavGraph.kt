package com.example.foodies.feature_foodies.presentation.navgraph

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.foodies.feature_foodies.presentation.Cart.CartScreen
import com.example.foodies.feature_foodies.presentation.Catalog.CatalogScreen
import com.example.foodies.feature_foodies.presentation.Catalog.CatalogViewModel
import com.example.foodies.feature_foodies.presentation.ProductDetails.ProductDetailsScreen
import com.example.foodies.feature_foodies.presentation.ProductDetails.ProductDetailsViewModel
import com.google.accompanist.systemuicontroller.SystemUiController
import kotlinx.coroutines.delay

fun NavGraphBuilder.appNavGraph(
    navHostController: NavController,
    startDestination: String,
    systemUiController: SystemUiController
) {


    navigation(startDestination, Route.AppNavGraph.route){
        composable(Route.Catalog.route){

            var exit by remember{ mutableStateOf(false)}
            val context = LocalContext.current as? Activity
            LaunchedEffect(key1 = exit){
                if (exit){
                    delay(2000)
                    exit = false
                }
            }
            BackHandler(true) {
                if (exit){
                    context?.finish()
                }else{
                    exit = true
                    Toast.makeText(context, "Нажмите еще раз для выхода", Toast.LENGTH_SHORT).show()
                }
            }

            systemUiController.setStatusBarColor(
                MaterialTheme.colorScheme.background
            )

            CatalogScreen(navHostController)
        }
        composable(Route.ProductDetails.route){
            systemUiController.setStatusBarColor(
                MaterialTheme.colorScheme.background
            )

            val viewModel: ProductDetailsViewModel = hiltViewModel()
            val id = it.arguments?.getString("id")

            LaunchedEffect(Unit){
                Log.i("ProductLog", id.toString())
                viewModel.getProduct(id?.toInt()?:0)
            }

            ProductDetailsScreen(
                navHostController,
                viewModel
            )
        }
        composable(Route.Card.route){
            systemUiController.setStatusBarColor(
                MaterialTheme.colorScheme.background
            )

            CartScreen(navHostController)

        }
    }



}