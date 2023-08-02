package com.example.hellocowcow.ui.composables

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hellocowcow.data.Data
import com.example.hellocowcow.domain.models.DomainAccount
import com.example.hellocowcow.domain.models.ItemNav
import com.example.hellocowcow.ui.screen.stats.CollectionScreen
import com.example.hellocowcow.ui.screen.home.HomeScreen
import com.example.hellocowcow.ui.screen.profile.ProfileScreen
import com.example.hellocowcow.ui.screen.stats.TokenScreen

@Composable
fun HostController(
    navHostController: NavHostController,
    account: DomainAccount
) {
    NavHost(
        navController = navHostController,
        startDestination = ItemNav.Home.route
    ) {Data.items.forEach { item ->
        composable(item.route){
            when (it.destination.route) {

                ItemNav.Home.route -> { HomeScreen(hiltViewModel()) }
                ItemNav.Token.route -> { TokenScreen(hiltViewModel()) }
                ItemNav.Stats.route -> { CollectionScreen(hiltViewModel(), hiltViewModel()) }
                ItemNav.Profile.route -> { ProfileScreen(hiltViewModel(), account) }
            }
        }
    }
    }
}