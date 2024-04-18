package com.example.foodies.feature_foodies.presentation.Cart.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.foodies.common.components.CustomCounter


@Composable
fun CartItem(
    name: String,
    image: Any,
    currentPrice: Int,
    oldPrice: Int?,
    count: Int,

    onPlusClickListener: () -> Unit,
    onMinusClickListener: () -> Unit,
    onItemClickListener: () -> Unit,

    modifier: Modifier
) {

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            MaterialTheme.colorScheme.background
        ),
        shape = RoundedCornerShape(0.dp),
        elevation = CardDefaults.cardElevation(
            0.dp
        ),
        onClick = onItemClickListener

    ) {
        Column {
            Row(
                Modifier
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = image),
                    contentDescription = null,
                    modifier = Modifier.size(96.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(Modifier.weight(1f)) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 2,
                        minLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        CustomCounter(
                            onMinusClickListener = onMinusClickListener,
                            onPlusClickListener = onPlusClickListener,
                            count = count.toString(),
                            isDefaultSize = true,
                            modifier = Modifier
                        )
                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = "$currentPrice ₽",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight(500)
                                )
                            )
                            if (oldPrice != null){
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "$oldPrice ₽",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = Color.Black.copy(0.6f)
                                    ),
                                    textDecoration = TextDecoration.LineThrough
                                )
                            }

                        }
                    }
                }
            }
            Divider(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.Black.copy(0.12f)))
        }

    }

}