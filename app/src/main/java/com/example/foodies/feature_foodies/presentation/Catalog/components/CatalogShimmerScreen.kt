package com.example.foodies.feature_foodies.presentation.Catalog.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.foodies.common.components.shimmerEffect

@Composable
fun CatalogShimmerScreen(
) {
    Column(Modifier.fillMaxSize()) {
        LazyRow(
            contentPadding = PaddingValues(start=16.dp, end=8.dp)
        ){
            items(5){
                Box(
                    Modifier
                        .clip(MaterialTheme.shapes.medium)
                        .width(83.dp)
                        .height(40.dp)
                        .shimmerEffect()
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        CatalogProductsShimmerLoading()

    }

}

@Composable
fun CatalogProductsShimmerLoading() {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),

        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        items(20){
            Box(
                Modifier
                    .background(
                        MaterialTheme.colorScheme.surface,
                        MaterialTheme.shapes.medium
                    )
            ){
                Column {
                    Box(
                        modifier = Modifier
                            .clip(MaterialTheme.shapes.medium)
                            .fillMaxWidth()
                            .height(170.dp)
                            .shimmerEffect(),
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Column(Modifier.padding(horizontal = 12.dp)) {
                        Box(
                            modifier = Modifier
                                .clip(MaterialTheme.shapes.medium)
                                .fillMaxWidth(0.6f)
                                .height(20.dp)
                                .shimmerEffect(),
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Box(
                            modifier = Modifier
                                .clip(MaterialTheme.shapes.medium)
                                .fillMaxWidth(0.3f)
                                .height(20.dp)
                                .shimmerEffect(),
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }

                }
            }
        }
    }
}