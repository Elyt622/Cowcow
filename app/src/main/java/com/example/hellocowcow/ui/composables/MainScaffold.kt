package com.example.hellocowcow.ui.composables

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScaffold(address: String) {

    val navController = rememberNavController()

    Scaffold(
        content = { HostController(navHostController = navController, address) },
        bottomBar = { BottomBar(navController = navController) }
    )
}