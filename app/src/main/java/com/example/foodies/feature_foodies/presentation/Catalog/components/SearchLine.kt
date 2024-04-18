package com.example.foodies.feature_foodies.presentation.Catalog.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.foodies.R


@Composable
fun SearchLine(
    state: Boolean,
    value: String,
    hilt: String,
    onBackClickListener: () -> Unit,
    onValueChangeListener: (String) -> Unit
) {

    var isBackClicked = remember{
        mutableStateOf(true)
    }

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
        val focusRequester = remember{FocusRequester()}
        Row(
            Modifier
                .padding(start = 18.dp, end = 16.dp, top = 20.dp, bottom = 20.dp)
                .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    onBackClickListener()
                },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_left),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            BasicTextField(
                value = value,
                onValueChange = onValueChangeListener,
                modifier = Modifier
                    .weight(1f)
                    .focusRequester(focusRequester),
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    textAlign = TextAlign.Start
                ),
                decorationBox = {
                    if (value.isEmpty()){
                        Text(text = hilt, style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.Black.copy(0.6f),
                            textAlign = TextAlign.Start
                        ))
                    }
                    it()
                }
            )
            Spacer(modifier = Modifier.width(16.dp))
            if (value.isNotEmpty()){
                IconButton(
                    onClick = {
                        onValueChangeListener("")
                    },
                    modifier = Modifier.size(24.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_cancel),
                        contentDescription = null
                    )
                }
            }
        }
        LaunchedEffect(key1 = Unit){
            focusRequester.requestFocus()
        }
    }



}