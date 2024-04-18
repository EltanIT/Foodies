package com.example.foodies.feature_foodies.presentation.ProductDetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun CustomSpecificationItem(
    name: String,
    value: String,
    modifier: Modifier
) {

    Divider(
        Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.Black.copy(0.12f))
    )
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 13.dp)
        ,
        horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.Black.copy(0.6f)
            )
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium
        )
    }
    
}