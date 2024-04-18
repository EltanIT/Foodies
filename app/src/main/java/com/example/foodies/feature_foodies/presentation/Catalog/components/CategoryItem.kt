package com.example.foodies.feature_foodies.presentation.Catalog.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CategoryItem(
    name: String,
    isSelected: Boolean,
    onClickListener: () -> Unit
) {

    Box(
        modifier = Modifier
            .padding(end=8.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background)
            .clickable { onClickListener() }
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
            ,
            text = name,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = if (isSelected) MaterialTheme.colorScheme.onPrimary else Color.Black.copy(0.87f)
            )
        )
    }
}