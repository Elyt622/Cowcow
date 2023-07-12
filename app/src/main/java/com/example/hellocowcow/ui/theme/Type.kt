package com.example.hellocowcow.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.hellocowcow.R

// Set of Material typography styles to start with

val Typography2 = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.fredoka_one)),
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    labelSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.fredoka_one)),
        fontWeight = FontWeight.Normal,
        fontSize = 11.5.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

