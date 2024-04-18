package com.example.foodies.feature_foodies.presentation.Catalog.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.foodies.R
import com.example.foodies.feature_foodies.presentation.ui.theme.PrimaryColor

@Composable
fun TopLine(
    state: Boolean,
    countSelectedTags: Int,
    onFilterClickListener: () -> Unit,
    onSearchClickListener: () -> Unit,
) {
    AnimatedVisibility(
        visible = state,
        modifier = Modifier.fillMaxWidth(),
        enter = slideInHorizontally()
                + expandHorizontally(expandFrom = Alignment.End)
                + fadeIn(),
        exit = slideOutHorizontally(targetOffsetX = { fullWidth -> fullWidth })
                + shrinkHorizontally()
                + fadeOut(),
    ) {
        Row(
            Modifier
                .padding(start = 8.dp, end = 8.dp, top = 24.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Box() {
                IconButton(
                    modifier = Modifier.size(44.dp),
                    onClick = onFilterClickListener
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_filter),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
                if (countSelectedTags>0){
                    Box(
                        Modifier
                            .padding(start = 3.dp)
                            .align(Alignment.TopEnd)
                            .size(17.dp)
                            .background(PrimaryColor, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = countSelectedTags.toString(),
                            style = MaterialTheme.typography.labelLarge.copy(
                                color = MaterialTheme.colorScheme.background
                            )
                        )
                    }
                }
            }


            Image(painter = painterResource(id = R.drawable.ic_logo), contentDescription = null)

            IconButton(
                modifier = Modifier.size(44.dp),
                onClick = onSearchClickListener
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }


}