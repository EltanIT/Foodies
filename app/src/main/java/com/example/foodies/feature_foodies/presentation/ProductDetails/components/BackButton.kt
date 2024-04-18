package com.example.foodies.feature_foodies.presentation.ProductDetails.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.foodies.R


@Composable
fun BackButton(
    onClickListener: () -> Unit
) {

    var isBackClicked = remember{
        mutableStateOf(true)
    }

    Card(
        onClick = {
            isBackClicked.value = !isBackClicked.value
            onClickListener()
        },
        shape = CircleShape,
        enabled = isBackClicked.value,
        elevation = CardDefaults.cardElevation(
            2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            disabledContainerColor = MaterialTheme.colorScheme.background,
        ),
        modifier = Modifier.padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_left),
            contentDescription = null,
            modifier = Modifier.padding(10.dp)
        )
    }
}