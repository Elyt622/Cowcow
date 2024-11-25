package com.example.hellocowcow.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.hellocowcow.R

// Set of Material typography styles to start with

val fredokaOneFont = Font(R.font.fredoka_one)

val Typography2 = Typography(

  bodyLarge = TextStyle(
    fontFamily = FontFamily(fredokaOneFont),
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.5.sp
  ),

  bodyMedium = TextStyle(
    fontFamily = FontFamily(fredokaOneFont),
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.5.sp
  ),

  labelSmall = TextStyle(
    fontFamily = FontFamily(fredokaOneFont),
    fontWeight = FontWeight.Normal,
    fontSize = 11.5.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.5.sp
  ),

  labelMedium = TextStyle(
    fontFamily = FontFamily(fredokaOneFont),
    fontWeight = FontWeight.Normal,
    fontSize = 12.5.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.5.sp
  ),

  titleLarge = TextStyle(
    fontFamily = FontFamily(fredokaOneFont),
    fontWeight = FontWeight.Bold,
    fontSize = 28.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.5.sp
  ),

  titleMedium = TextStyle(
    fontFamily = FontFamily(fredokaOneFont),
    fontWeight = FontWeight.Bold,
    fontSize = 20.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.5.sp
  ),

  titleSmall = TextStyle(
    fontFamily = FontFamily(fredokaOneFont),
    fontWeight = FontWeight.Bold,
    fontSize = 18.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.5.sp
  )
)

