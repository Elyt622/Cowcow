package com.example.hellocowcow.ui.screen.profile.tabs

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.hellocowcow.ui.composables.NftCard
import com.example.hellocowcow.ui.composables.onClicked
import com.example.hellocowcow.ui.theme.Typography2
import com.example.hellocowcow.ui.viewmodels.screen.profile.WalletViewModel
import es.dmoral.toasty.Toasty

@Composable
fun WalletScreen(
    viewModel: WalletViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    val functionExecuted = remember { mutableStateOf(false) }

    // LaunchedEffect runs when the Composable starts up
    LaunchedEffect(Unit) {
        if (!functionExecuted.value) {
            viewModel.getCowsInWallet()
            functionExecuted.value = true
        }
    }

    when (uiState) {
        is WalletViewModel.UiState.Success -> {
            (uiState as WalletViewModel.UiState.Success).data.let { nfts ->
                Text(
                    modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                    text = "Cows: " + nfts.size.toString(),
                    style = MaterialTheme.typography.labelMedium
                )
                LazyVerticalGrid(
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 50.dp),
                    columns = GridCells.Adaptive(150.dp),
                    content = {
                        items(nfts) { nft ->
                            NftCard(nft = nft) {
                                nft.identifier?.let {
                                    onClicked(context, it)
                                }
                            }
                        }
                    }
                )
            }
        }
        is WalletViewModel.UiState.Loading -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.width(60.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }
        is WalletViewModel.UiState.Error -> {
            Toasty.error(
                LocalContext.current,
                (uiState as WalletViewModel.UiState.Error).error,
                Toast.LENGTH_LONG
            ).show()
        }
        is WalletViewModel.UiState.NoData -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No NFT in the wallet !",
                    style = Typography2.bodyLarge
                )
            }
        }
    }
}

