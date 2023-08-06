package com.example.hellocowcow.ui.composables

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hellocowcow.data.Data
import com.example.hellocowcow.domain.models.DomainAccount
import com.example.hellocowcow.domain.models.ItemNav
import com.example.hellocowcow.ui.screen.TestScreen
import com.example.hellocowcow.ui.screen.home.HomeScreen
import com.example.hellocowcow.ui.screen.profile.ProfileScreen
import com.example.hellocowcow.ui.screen.stats.StatsScreen

@Composable
fun HostController(
    navHostController: NavHostController,
    account: DomainAccount,
    topic: String
) {
    NavHost(
        navController = navHostController,
        startDestination = ItemNav.Home.route
    ) {Data.items.forEach { item ->
        composable(item.route){
            when (it.destination.route) {

                ItemNav.Home.route -> { HomeScreen(hiltViewModel()) }
                ItemNav.Token.route -> { TestScreen(account, topic, hiltViewModel()) }
                ItemNav.Stats.route -> { StatsScreen() }
                ItemNav.Profile.route -> { ProfileScreen(account) }
            }
        }
    }
    }
}