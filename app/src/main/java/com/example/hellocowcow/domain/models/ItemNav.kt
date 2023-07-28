package com.example.hellocowcow.domain.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QueryStats
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ItemNav(
    val label : String,
    val icon : ImageVector,
    val route : String
) {
    object Home : ItemNav("Home", Icons.Filled.Home, "Home")
    object Token : ItemNav("Token", Icons.Filled.Money, "Token")
    object Stats : ItemNav("Stats", Icons.Filled.QueryStats, "Stats")
    object Profile : ItemNav("Profile", Icons.Filled.Person, "Profile")
}