package com.example.hellocowcow.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.hellocowcow.ui.viewmodels.screen.ProfileViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    name: String
) {


    Column(
        Modifier.padding(bottom = 50.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Hello $name !",
            fontSize = 22.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        TabScreen()
    }
}

@Composable
fun TabScreen() {

    var tabIndex by remember { mutableStateOf(0) }

    val tabs = listOf("Wallet", "Staked", "MarketPlace")

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(
            selectedTabIndex = tabIndex,
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.background
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title, style = MaterialTheme.typography.labelMedium) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    selectedContentColor = MaterialTheme.colorScheme.background,
                    unselectedContentColor = MaterialTheme.colorScheme.background
                )
            }
        }
        when (tabIndex) {
            0 -> WalletScreen(hiltViewModel())
            1 -> StakeScreen(hiltViewModel())
            2 -> MarketScreen(hiltViewModel())
        }
    }
}

