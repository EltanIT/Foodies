package com.example.foodies.feature_foodies.presentation.Catalog.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.foodies.common.components.CustomCounter

@Composable
fun ProductItem(
    title: String,
    measure: String,
    currentPrice: Int,
    oldPrice: Int?,
    image: Any,
    @DrawableRes icon: Int? = null,
    isSavingInCard: Boolean,
    countInCard: Int = 1,
    onCardClickListener: () -> Unit,
    onAddInCardClickListener: () -> Unit,
    onMinusClickListener: () -> Unit,
    onPlusClickListener: () -> Unit,
    modifier: Modifier
) {
    
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(
            0.dp
        ),
        onClick = onCardClickListener,
        colors = CardDefaults.cardColors(
            MaterialTheme.colorScheme.surface
        )
    ) {
        Box{
            Column {
                Image(
                    painter = rememberAsyncImagePainter(model = image),
                    contentDescription = null,
                    modifier = Modifier.height(170.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(12.dp))
                Column(Modifier.padding(horizontal = 12.dp)) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = measure,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color.Black.copy(0.6f)
                        )
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    if (isSavingInCard){
                        CustomCounter(
                            onMinusClickListener = onMinusClickListener,
                            onPlusClickListener = onPlusClickListener,
                            count = countInCard.toString(),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }else{
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = MaterialTheme.shapes.medium,
                            elevation = CardDefaults.cardElevation(
                                2.dp
                            ),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            onClick = onAddInCardClickListener
                        ) {
                            if (oldPrice==null){
                                Text(
                                    modifier = Modifier
                                        .padding(vertical = 12.dp)
                                        .fillMaxWidth(),
                                    text = "$currentPrice ₽",
                                    style = MaterialTheme.typography.bodyLarge,
                                )
                            }
                            else{
                                Row(
                                    modifier = Modifier
                                        .padding(vertical = 12.dp)
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "$currentPrice ₽",
                                        style = MaterialTheme.typography.bodyLarge,
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "$oldPrice ₽",
                                        style = MaterialTheme.typography.bodyLarge.copy(
                                            fontSize = 14.sp,
                                            color = Color.Black.copy(0.6f),
                                            textDecoration = TextDecoration.LineThrough
                                        ),
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                }

            }


            if (icon!=null){
                Image(
                    modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                    painter = painterResource(id = icon),
                    contentDescription = null
                )
            }
        }

    }
}