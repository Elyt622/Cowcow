package com.example.hellocowcow.ui.screen

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.hellocowcow.ui.theme.Typography2
import com.example.hellocowcow.ui.viewmodels.screen.StakeViewModel

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalFoundationApi::class)
@Composable
fun StakeScreen(
    viewModel: StakeViewModel
) {

    val uiState by viewModel.uiState.collectAsState()

    val cardColors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.Black
    )
    val pagerState = rememberPagerState()

    when (uiState) {
        is StakeViewModel.UiState.Success -> {
            LazyVerticalGrid(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 40.dp),
                columns = GridCells.Adaptive(150.dp),
                content = {
                    items((uiState as StakeViewModel.UiState.Success).data) { nft ->
                        ElevatedCard(
                            colors = cardColors,
                            modifier = Modifier.padding(8.dp)
                        ) {
                            HorizontalPager(
                                state = pagerState,
                                pageCount = 2,
                           ) { index ->
                                GlideImage(
                                    model = arrayOf(nft.url,"https://xoxno.com/api/getCow?identifier=${nft.identifier}")[index],
                                    contentDescription = nft.collection,
                                    modifier = Modifier
                                        .padding(start = 8.dp, end = 8.dp, top = 8.dp)
                                )
                            }
                            Column(
                                Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    color = MaterialTheme.colorScheme.background,
                                    text = nft.name.toString(),
                                    style = Typography2.bodyLarge
                                )
                                Text(
                                    color = MaterialTheme.colorScheme.background,
                                    text = "Rank : " + nft.rank.toString(),
                                    style = Typography2.labelMedium
                                )
                            }
                        }
                    }
                }
            )
        }
        is StakeViewModel.UiState.Loading -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.width(60.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }
        is StakeViewModel.UiState.Error -> {
            Toast.makeText(
                LocalContext.current,
                (uiState as StakeViewModel.UiState.Error).error,
                Toast.LENGTH_LONG
            ).show()
        }
        is StakeViewModel.UiState.NoData -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No NFT in staking !",
                    style = Typography2.bodyLarge
                )
            }
        }
    }
}





