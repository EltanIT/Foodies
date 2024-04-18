package com.example.foodies.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.foodies.R


@Composable
fun CustomCounter(
    onMinusClickListener: () -> Unit,
    onPlusClickListener: () -> Unit,
    count: String,
    color: Color = Color.White,
    isDefaultSize: Boolean = false,
    modifier: Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomCounterMinus(
            color = color
        ) {
            onMinusClickListener()
        }
        Text(
            text = count,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight(500)
            ),
            modifier = Modifier.padding(horizontal =if (isDefaultSize)12.dp else 0.dp)
        )
        CustomCounterPlus(
            color = color
        ) {
            onPlusClickListener()
        }
    }
}


@Composable
fun CustomCounterMinus(
    color: Color,
    onClickListener: () -> Unit,
) {

    Card(
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(
            4.dp
        ),
        colors = CardDefaults.cardColors(
            color
        ),
        onClick = onClickListener
    ) {
        Box(
            Modifier
                .padding(horizontal = 12.dp, vertical = 19.dp)
                .height(2.dp)
                .width(16.dp)
                .background(MaterialTheme.colorScheme.primary, CircleShape)
        )
    }
}

@Composable
fun CustomCounterPlus(
    color: Color,
    onClickListener: () -> Unit,
) {

    Card(
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(
            4.dp
        ),
        colors = CardDefaults.cardColors(
            color
        ),
        onClick = onClickListener
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_plus),
            contentDescription = null,
            modifier = Modifier.padding(8.dp)
        )
    }
}