package com.example.foodies.feature_foodies.presentation.ProductDetails.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.core.util.toRange
import com.example.foodies.common.components.shimmerEffect


@Composable
fun ProductDetailsShimmerScreen(
    modifier: Modifier = Modifier
) {

    Column(modifier) {
        Box(
            Modifier
                .padding(horizontal = 16.dp)
                .clip(MaterialTheme.shapes.medium)
                .fillMaxHeight(0.4f)
                .fillMaxWidth()
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(24.dp))
        Box(
            Modifier
                .clip(MaterialTheme.shapes.medium)
                .fillMaxWidth(0.3f)
                .height(36.dp)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(12.dp))
        Box(
            Modifier
                .clip(MaterialTheme.shapes.medium)
                .fillMaxWidth(0.9f)
                .height(20.dp)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(5.dp))
        Box(
            Modifier
                .clip(MaterialTheme.shapes.medium)
                .fillMaxWidth(0.6f)
                .height(20.dp)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(){
            items(5){
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Box(
                        Modifier
                            .clip(MaterialTheme.shapes.medium)
                            .fillMaxWidth(
                                (3..8)
                                    .random()
                                    .toFloat() / 10
                            )
                            .height(20.dp)
                            .shimmerEffect()
                    )
                }
                Spacer(modifier = Modifier.height(26.dp))
            }
        }
    }

}