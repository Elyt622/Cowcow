package com.example.hellocowcow.ui.screen.profile

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hellocowcow.R
import com.example.hellocowcow.domain.models.DomainAccount
import com.example.hellocowcow.ui.composables.CustomAlert
import com.example.hellocowcow.ui.screen.profile.tabs.MarketScreen
import com.example.hellocowcow.ui.screen.profile.tabs.StakeScreen
import com.example.hellocowcow.ui.screen.profile.tabs.WalletScreen
import com.example.hellocowcow.ui.viewmodels.screen.profile.MarketViewModel
import com.example.hellocowcow.ui.viewmodels.screen.profile.ProfileViewModel
import com.example.hellocowcow.ui.viewmodels.screen.profile.StakeViewModel
import com.example.hellocowcow.ui.viewmodels.screen.profile.WalletViewModel
import com.reown.sign.client.SignClient
import es.dmoral.toasty.Toasty
import timber.log.Timber

@Composable
fun ProfileScreen(
    account: DomainAccount,
    topic: String,
    viewModel: ProfileViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val uiStateTx by viewModel.uiStateTx.collectAsState()
    val context = LocalContext.current

    val functionExecuted = remember { mutableStateOf(false) }

    // LaunchedEffect runs when the Composable starts up
    LaunchedEffect(Unit) {
        if (!functionExecuted.value) {
            viewModel.setAddress(account.address)
            viewModel.getUnclaimedMooveForUser()
            functionExecuted.value = true
        }
    }
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
        ) {
            Text(
                text = "Hello ${account.username.substringBefore(".elrond")}",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .weight(1.0F)
                    .padding(start = 12.dp),
            )

            Button(
                modifier = Modifier
                    .weight(1.0F)
                    .padding(end = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.background,
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                onClick = {
                    var error = false
                    SignClient.request(
                        request = viewModel.buildClaimRewardRequest(account, topic),
                        onError = { err ->
                            error = true
                            Timber.tag("ERROR").e(err.throwable)
                        }
                    )
                    if (!error) {
                        Toasty.info(
                            context,
                            "Request sent to xPortal",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            ) {
                when (uiState) {
                    is ProfileViewModel.UiState.Success -> {
                        (uiState as ProfileViewModel.UiState.Success)
                            .data.let { data ->
                                Text(
                                    text = "Claim $data",
                                    style = MaterialTheme.typography.labelMedium,
                                )
                                Image(
                                    ImageVector.vectorResource(id = R.drawable.moovelogo),
                                    "Moove Logo",
                                    modifier = Modifier
                                        .size(16.dp)
                                        .padding(start = 4.dp)
                                )
                            }
                    }

                    is ProfileViewModel.UiState.Loading -> {
                        Box(
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(15.dp),
                                color = MaterialTheme.colorScheme.background
                            )
                        }
                    }

                    else -> {}
                }
            }
        }
        when (uiStateTx) {
            is ProfileViewModel.UiStateTx.Send -> {
                (uiStateTx as ProfileViewModel.UiStateTx.Send)
                    .tx.let { tx ->
                        CustomAlert(
                            tx = tx
                        )
                    }
            }

            is ProfileViewModel.UiStateTx.Error -> {
                (uiStateTx as ProfileViewModel.UiStateTx.Error)
                    .error.let { err ->
                        Toasty.error(
                            LocalContext.current,
                            err,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }

            else -> {}
        }
        TabScreen(account)
    }
}

@Composable
fun TabScreen(account: DomainAccount) {

    var tabIndex by remember { mutableIntStateOf(0) }

    val tabs = listOf("Wallet", "Staked", "Market")

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(
            selectedTabIndex = tabIndex,
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.background
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title, style = MaterialTheme.typography.bodyMedium) },
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

