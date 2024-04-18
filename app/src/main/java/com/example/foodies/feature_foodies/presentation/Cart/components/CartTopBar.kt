package com.example.foodies.feature_foodies.presentation.Cart.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.foodies.R
import com.example.foodies.feature_foodies.presentation.Catalog.components.TopLine


@Composable
fun CartTopBar(
    onClickListener: () -> Unit
) {

   var isBackClicked = remember{
       mutableStateOf(true)
   }
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(0.dp),
        elevation = CardDefaults.cardElevation(
            5.dp
        ),
        colors = CardDefaults.cardColors(
            MaterialTheme.colorScheme.background
        ),
    ) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = {
                isBackClicked.value = !isBackClicked.value
                onClickListener()
            },
                enabled = isBackClicked.value,
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_left),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.width(32.dp))
            Text(text = "Корзина", style = MaterialTheme.typography.headlineSmall)
        }
    }
}