package com.example.foodies.feature_foodies.presentation.ProductDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.foodies.common.components.CustomCounter
import com.example.foodies.common.components.CustomPrimaryButton
import com.example.foodies.feature_foodies.presentation.ProductDetails.components.BackButton
import com.example.foodies.feature_foodies.presentation.ProductDetails.components.CustomSpecificationItem
import com.example.foodies.feature_foodies.presentation.ProductDetails.components.ProductDetailsShimmerScreen


@Composable
fun ProductDetailsScreen(
    navController: NavController,
    viewModel: ProductDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    if (state.error.isNotEmpty()){
        Box(Modifier.fillMaxSize()) {
            Text(
                text = state.error,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Black.copy(0.6f)
                ),
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

    Box(Modifier.fillMaxSize()) {
        if (state.isLoading){
            ProductDetailsShimmerScreen(
                Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )
        }
        else{
            if (state.product != null){
                Column(Modifier.fillMaxSize()) {
                    LazyColumn(Modifier.weight(1f)){
                        item {
                            Image(
                                painter = rememberAsyncImagePainter(model = state.product?.image),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(345.dp),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.height(24.dp))
                            Text(
                                text = state.product?.name.toString(),
                                style = MaterialTheme.typography.headlineLarge.copy(
                                    textAlign = TextAlign.Start
                                ),
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = state.product?.description.toString(),
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = Color.Black.copy(0.6f),
                                    textAlign = TextAlign.Start
                                ),
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                            Spacer(modifier = Modifier.height(24.dp))
                        }
                        itemsIndexed(state.productSpecificationList){index, item ->
                            CustomSpecificationItem(
                                name = item.name,
                                value = item.value,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                    if (state.cardProduct!=null){
                        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                            CustomCounter(
                                onMinusClickListener = { viewModel.onEvent(ProductsDetailsEvent.MinusProductCount) },
                                onPlusClickListener = { viewModel.onEvent(ProductsDetailsEvent.PlusProductCount) },
                                count = state.cardProduct.count.toString(),
                                modifier = Modifier
                                    .padding(horizontal = 16.dp, vertical = 12.dp)
                                    .fillMaxWidth()
                            )
                        }

                    }else{
                        CustomPrimaryButton(
                            title = "В корзину за ${state.product?.price_current?.div(100)} ₽",
                            onClickListener = {viewModel.onEvent(ProductsDetailsEvent.AddInCard)},
                        )
                    }
                }
            }
        }


        BackButton {
            navController.popBackStack()
        }
    }

}