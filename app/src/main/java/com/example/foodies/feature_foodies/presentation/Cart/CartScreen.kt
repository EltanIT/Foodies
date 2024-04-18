@file:OptIn(ExperimentalFoundationApi::class)

package com.example.foodies.feature_foodies.presentation.Cart

import android.annotation.SuppressLint
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.foodies.common.components.CustomPrimaryButton
import com.example.foodies.feature_foodies.presentation.Cart.components.CartItem
import com.example.foodies.feature_foodies.presentation.Cart.components.CartTopBar
import com.example.foodies.feature_foodies.presentation.navgraph.Route


@SuppressLint("UnusedCrossfadeTargetStateParameter")
@Composable
fun CartScreen(
    navController: NavController,
    viewModel: CartViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        CartTopBar {
            navController.popBackStack()
        }

        if (state.cartList.isEmpty()){
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Пусто, выберите блюдо \nв каталоге :)", style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Black.copy(0.6f)
                    )
                )
            }
        }else{
            Column(Modifier.fillMaxSize()) {
                LazyColumn(Modifier.weight(1f)){
                    items(
                        count = state.cartList.size,
                        key = { state.cartList[it].id?:0 }
                    ){index ->
                        CartItem(
                            name = state.cartList[index].name,
                            image = state.cartList[index].image,
                            currentPrice = state.cartList[index].price_current/100,
                            oldPrice = state.cartList[index].price_old?.div(100),
                            count = state.cartList[index].count?:1,
                            onPlusClickListener = { viewModel.onEvent(CartEvent.PlusProductCount(state.cartList[index].id?:0)) },
                            onMinusClickListener = { viewModel.onEvent(CartEvent.MinusProductCount(state.cartList[index].id?:0)) },
                            modifier = Modifier.animateItemPlacement(),
                            onItemClickListener = {
                                navController.navigate(Route.ProductDetails.route.replace("{id}", state.cartList[index].id.toString()))
                            }
                        )
                    }
                }
                CustomPrimaryButton(
                    title = "Заказать за ${state.cartList.map { it.price_current.times(it.count?:1)}.sum()/100} ₽",
                    onClickListener = {  })
            }

        }


    }
}