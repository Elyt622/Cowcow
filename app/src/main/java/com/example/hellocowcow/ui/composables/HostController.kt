package com.example.hellocowcow.ui.composables

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hellocowcow.datas.Datas
import com.example.hellocowcow.domain.models.ItemNav
import com.example.hellocowcow.ui.screen.CollectionScreen
import com.example.hellocowcow.ui.screen.HomeScreen
import com.example.hellocowcow.ui.screen.ProfileScreen
import com.example.hellocowcow.ui.screen.TokenScreen

@Composable
fun HostController(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = ItemNav.Home.route
    ) {Datas.items.forEach { item ->
        composable(item.route){
            when (it.destination.route) {

                ItemNav.Home.route -> { HomeScreen(hiltViewModel()) }
                ItemNav.Token.route -> { TokenScreen(hiltViewModel()) }
                ItemNav.Collection.route -> { CollectionScreen(hiltViewModel()) }
                ItemNav.Profile.route -> { ProfileScreen(hiltViewModel(), name = "Profile") }
            }
        }
    }
    }
}