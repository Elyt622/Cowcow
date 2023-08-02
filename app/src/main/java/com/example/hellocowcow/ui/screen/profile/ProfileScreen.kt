package com.example.hellocowcow.ui.screen.profile

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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hellocowcow.domain.models.DomainAccount
import com.example.hellocowcow.ui.screen.profile.tabs.MarketScreen
import com.example.hellocowcow.ui.screen.profile.tabs.StakeScreen
import com.example.hellocowcow.ui.screen.profile.tabs.WalletScreen
import com.example.hellocowcow.ui.viewmodels.screen.profile.MarketViewModel
import com.example.hellocowcow.ui.viewmodels.screen.profile.StakeViewModel
import com.example.hellocowcow.ui.viewmodels.screen.profile.WalletViewModel

@Composable
fun ProfileScreen(
    account: DomainAccount
) {

    Column(
        Modifier.padding(bottom = 50.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Hello ${account.username.substringBefore(".elrond")} !",
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelMedium
        )
        TabScreen(account)
    }
}

@Composable
fun TabScreen(account: DomainAccount) {

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
            0 -> {
                val viewModel: WalletViewModel = hiltViewModel()
                viewModel.setAddress(account.address)
                WalletScreen(viewModel)
            }
            1 -> {
                val viewModel: StakeViewModel = hiltViewModel()
                viewModel.setAddress(account.address)
                StakeScreen(viewModel)
            }
            2 -> {
                val viewModel: MarketViewModel = hiltViewModel()
                viewModel.setAddress(account.address)
                MarketScreen(viewModel)
            }
        }
    }
}

