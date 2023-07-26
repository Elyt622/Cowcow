package com.example.hellocowcow.ui.composables

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hellocowcow.data.Data
import com.example.hellocowcow.domain.models.ItemNav
import com.example.hellocowcow.ui.screen.collection.CollectionScreen
import com.example.hellocowcow.ui.screen.home.HomeScreen
import com.example.hellocowcow.ui.screen.profile.ProfileScreen
import com.example.hellocowcow.ui.screen.token.TokenScreen

@Composable
fun HostController(
    navHostController: NavHostController,
    address: String
) {
    NavHost(
        navController = navHostController,
        startDestination = ItemNav.Home.route
    ) {Data.items.forEach { item ->
        composable(item.route){
            when (it.destination.route) {

                ItemNav.Home.route -> { HomeScreen(hiltViewModel()) }
                ItemNav.Token.route -> { TokenScreen(hiltViewModel()) }
                ItemNav.Collection.route -> { CollectionScreen(hiltViewModel(), hiltViewModel()) }
                ItemNav.Profile.route -> { ProfileScreen(hiltViewModel(), address) }
            }
        }
    }
    }
}