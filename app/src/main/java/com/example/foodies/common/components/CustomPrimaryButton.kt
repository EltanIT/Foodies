package com.example.foodies.common.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun CustomPrimaryButton(
    @DrawableRes icon: Int? = null,
    title: String,
    onClickListener: () -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth()
) {

    Box(Modifier
        .fillMaxWidth()
    ) {
        Card(
            onClick = onClickListener,
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(
                MaterialTheme.colorScheme.primary
            ),
            elevation = CardDefaults.cardElevation(
                0.dp
            ),
            modifier = modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .height(48.dp)
        ) {
            Row(
                Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (icon!=null){
                    Image(painter = painterResource(id = icon), contentDescription = null)
                }
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }

        }
    }

}