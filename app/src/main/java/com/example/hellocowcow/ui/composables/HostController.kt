package com.example.hellocowcow.ui.composables

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hellocowcow.datas.Datas
import com.example.hellocowcow.domain.models.ItemNav
import com.example.hellocowcow.ui.screen.CollectionScreen
import com.example.hellocowcow.ui.screen.HomeScreen
import com.example.hellocowcow.ui.screen.ProfileScreen
import com.example.hellocowcow.ui.screen.TokenScreen
import com.example.hellocowcow.ui.viewmodels.TokenViewModel

@Composable
fun HostController(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = ItemNav.Home.route
    ) {Datas.items.forEach { item ->
        composable(item.route){
            val viewModel: TokenViewModel = hiltViewModel()
            when (it.destination.route) {

                ItemNav.Home.route -> { HomeScreen(viewModel()) }
                ItemNav.Token.route -> { TokenScreen(viewModel) }
                ItemNav.Collection.route -> { CollectionScreen(viewModel()) }
                ItemNav.Profile.route -> { ProfileScreen(viewModel(), name = "Profile") }
            }
        }
    }
    }
}