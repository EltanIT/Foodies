package com.example.foodies.feature_foodies.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.foodies.R


val Roboto = listOf(
    Font(R.font.roboto_light, FontWeight.W100),
    Font(R.font.roboto_light, FontWeight.W200),
    Font(R.font.roboto_light, FontWeight.W300),
    Font(R.font.roboto_regular, FontWeight.W400),
    Font(R.font.roboto_medium, FontWeight.W500),
    Font(R.font.roboto_bold, FontWeight.W600),
    Font(R.font.roboto_bold, FontWeight.W700),
    Font(R.font.roboto_black, FontWeight.W800),
    Font(R.font.roboto_black, FontWeight.W900),
)

// Set of Material typography styles to start with
val Typography = Typography(

    headlineLarge = TextStyle(
        fontSize = 34.sp,
        lineHeight = 36.sp,
        fontWeight = FontWeight(400),
        fontFamily = FontFamily(Roboto),
        textAlign = TextAlign.Center,
        color = Color.Black
    ),

    headlineMedium = TextStyle(
        fontSize = 20.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight(500),
        fontFamily = FontFamily(Roboto),
        textAlign = TextAlign.Center,
        color = Color.Black
    ),
    headlineSmall = TextStyle(
        fontSize = 18.sp,
        lineHeight = 22.sp,
        fontWeight = FontWeight(600),
        fontFamily = FontFamily(Roboto),
        textAlign = TextAlign.Center,
        color = Color.Black
    ),


    bodyLarge = TextStyle(
        fontSize = 16.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight(500),
        fontFamily = FontFamily(Roboto),
        textAlign = TextAlign.Center,
        color = Color.Black
    ),
    bodyMedium = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight(400),
        fontFamily = FontFamily(Roboto),
        textAlign = TextAlign.Center,
        color = Color.Black
    ),
    bodySmall = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight(400),
        fontFamily = FontFamily(Roboto),
        textAlign = TextAlign.Center,
        color = Color.Black
    ),

    labelLarge = TextStyle(
        fontSize = 11.sp,
        lineHeight = 13.sp,
        fontWeight = FontWeight(600),
        fontFamily = FontFamily(Roboto),
        textAlign = TextAlign.Center,
        color = Color.Black
    ),

)