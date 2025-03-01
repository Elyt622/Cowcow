package com.example.hellocowcow.ui.composables

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.hellocowcow.domain.models.DomainAccount

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScaffold(
  account: DomainAccount,
  topic: String?
) {

  val navController = rememberNavController()

  if (topic != null)
    Scaffold(
      content = { HostController(navHostController = navController, account, topic) },
      bottomBar = { BottomBar(navController = navController) }
    )
}