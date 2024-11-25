package com.example.hellocowcow.ui.screen.home

import android.widget.Toast
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.hellocowcow.R
import com.example.hellocowcow.domain.models.ItemNav
import com.example.hellocowcow.ui.viewmodels.screen.home.HomeViewModel
import es.dmoral.toasty.Toasty
import java.util.Locale

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel
) {

    val uiState by viewModel.uiState.collectAsState()
    val uiStateSold by viewModel.uiStateSold.collectAsState()
    val isDarkTheme = isSystemInDarkTheme()

    val cardColors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.Black
    )
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .height(680.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 30.dp)
        ) {
            Text(
                text = "HELLO",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "COWCOW",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.size(10.dp))

            Row(
                modifier = Modifier.padding(8.dp)
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.background
                    ),
                    modifier = Modifier.padding(end = 20.dp),
                    onClick = {
                        navController.navigate(ItemNav.Stats.route)
                    }
                ) {
                    Text(
                        text = "STATS",
                        style = MaterialTheme.typography.titleSmall
                    )
                }

                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.background
                    ),
                    modifier = Modifier.padding(start = 20.dp),
                    onClick = {
                        navController.navigate(ItemNav.Profile.route)
                    }
                ) {
                    Text(
                        text = "PROFILE",
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }

            if(isDarkTheme)
                GlideImage(
                    modifier = Modifier
                        .fillMaxWidth(),
                    model = R.drawable.home_dark,
                    contentDescription = "Cowcow image",
                    contentScale = ContentScale.Crop
                )
            else
                GlideImage(
                    modifier = Modifier
                        .fillMaxWidth(),
                    model = R.drawable.home_light,
                    contentDescription = "Cowcow image",
                    contentScale = ContentScale.Crop
                )

            Spacer(modifier = Modifier.size(10.dp))

            ElevatedCard(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.background
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "KEY STATISTICS",
                    style = MaterialTheme.typography.titleSmall
                )
            }

            Spacer(modifier = Modifier.size(10.dp))

            Row(
                Modifier
                    .padding(start = 8.dp, end = 8.dp)
            ) {
                Card(
                    Modifier
                        .padding(8.dp)
                        .width(160.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = Color.Black
                    )
                ) {
                    Spacer(modifier = Modifier.size(10.dp))
                    ElevatedCard(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.background
                        ),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            modifier = Modifier.padding(10.dp),
                            text = "MOOVE PRICE",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    Row(
                        Modifier
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Column(
                            Modifier
                                .padding(8.dp)
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            ElevatedCard(
                                colors = cardColors,
                                elevation = CardDefaults.cardElevation(20.dp)
                            ) {
                                Row(
                                    Modifier.padding(8.dp)
                                ) {
                                    when(uiState) {
                                        is HomeViewModel.UiState.Success -> {
                                            (uiState as HomeViewModel.UiState.Success)
                                                .data.let { stats ->
                                                    Text(
                                                        modifier = Modifier.padding(10.dp),
                                                        text = String.format(Locale.getDefault(),
                                                            "%.3f",
                                                            stats.moovePrice
                                                        ),
                                                        style = MaterialTheme.typography.titleSmall
                                                    )

                                                    Image(
                                                        modifier = Modifier
                                                            .align(Alignment.CenterVertically)
                                                            .size(16.dp),
                                                        imageVector = ImageVector
                                                            .vectorResource(id = R.drawable.usdc),
                                                        contentDescription = "Stable coin USDC logo"
                                                    )
                                                }
                                        }
                                        is HomeViewModel.UiState.Loading -> {
                                            Box(
                                                contentAlignment = Alignment.Center
                                            ) {
                                                CircularProgressIndicator(
                                                    modifier = Modifier.width(15.dp),
                                                    color = MaterialTheme.colorScheme.background
                                                )
                                            }
                                        }
                                        is HomeViewModel.UiState.Error -> {
                                            Toasty.error(
                                                LocalContext.current,
                                                (uiState as HomeViewModel.UiState.Error).error,
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                    }


                                }
                            }
                        }
                    }
                }

                Card(
                    Modifier
                        .padding(8.dp)
                        .width(160.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = Color.Black
                    )
                ) {
                    Spacer(modifier = Modifier.size(10.dp))
                    ElevatedCard(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.background
                        ),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            modifier = Modifier.padding(10.dp),
                            text = "MARKETCAP",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    Row(
                        Modifier
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Column(
                            Modifier
                                .padding(8.dp)
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            ElevatedCard(
                                colors = cardColors,
                                elevation = CardDefaults.cardElevation(20.dp)
                            ) {
                                Row(
                                    Modifier.padding(8.dp)
                                ) {
                                    when(uiState) {
                                        is HomeViewModel.UiState.Success -> {
                                            (uiState as HomeViewModel.UiState.Success)
                                                .data.let { stats ->
                                                    Text(
                                                        modifier = Modifier.padding(10.dp),
                                                        text = String.format(
                                                            Locale.getDefault(),
                                                            "%.0f",
                                                            stats.mooveMC
                                                        ) + "$",
                                                        style = MaterialTheme.typography.titleSmall
                                                    )
                                                }
                                        }
                                        is HomeViewModel.UiState.Loading -> {
                                            Box(
                                                contentAlignment = Alignment.Center
                                            ) {
                                                CircularProgressIndicator(
                                                    modifier = Modifier.width(15.dp),
                                                    color = MaterialTheme.colorScheme.background
                                                )
                                            }
                                        }
                                        is HomeViewModel.UiState.Error -> {}
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Row(
                Modifier
                    .padding(start = 8.dp, end = 8.dp)
            ) {
                Card(
                    Modifier
                        .padding(8.dp)
                        .width(160.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = Color.Black
                    )
                ) {
                    Spacer(modifier = Modifier.size(10.dp))
                    ElevatedCard(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.background
                        ),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            modifier = Modifier.padding(10.dp),
                            text = "COWS STAKED",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    Row(
                        Modifier
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Column(
                            Modifier
                                .padding(8.dp)
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            ElevatedCard(
                                colors = cardColors,
                                elevation = CardDefaults.cardElevation(20.dp)
                            ) {
                                Row(
                                    Modifier.padding(8.dp)
                                ) {
                                    when(uiState) {
                                        is HomeViewModel.UiState.Success -> {
                                            (uiState as HomeViewModel.UiState.Success)
                                                .data.let { stats ->
                                                    Text(
                                                        modifier = Modifier.padding(10.dp),
                                                        text = stats.stakedCount,
                                                        style = MaterialTheme.typography.titleSmall
                                                    )
                                                }
                                        }
                                        is HomeViewModel.UiState.Loading -> {
                                            Box(
                                                contentAlignment = Alignment.Center
                                            ) {
                                                CircularProgressIndicator(
                                                    modifier = Modifier.width(15.dp),
                                                    color = MaterialTheme.colorScheme.background
                                                )
                                            }
                                        }
                                        is HomeViewModel.UiState.Error -> {}
                                    }
                                }
                            }
                        }
                    }
                }

                Card(
                    Modifier
                        .padding(8.dp)
                        .width(160.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = Color.Black
                    )
                ) {
                    Spacer(modifier = Modifier.size(10.dp))
                    ElevatedCard(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.background
                        ),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            modifier = Modifier.padding(10.dp),
                            text = "COWS LISTED",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    Row(
                        Modifier
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Column(
                            Modifier
                                .padding(8.dp)
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            ElevatedCard(
                                colors = cardColors,
                                elevation = CardDefaults.cardElevation(20.dp)
                            ) {
                                Row(
                                    Modifier.padding(8.dp)
                                ) {
                                    when(uiState) {
                                        is HomeViewModel.UiState.Success -> {
                                            (uiState as HomeViewModel.UiState.Success)
                                                .data.let { stats ->
                                                    Text(
                                                        modifier = Modifier.padding(10.dp),
                                                        text = stats.listedCount,
                                                        style = MaterialTheme.typography.titleSmall
                                                    )
                                                }
                                        }
                                        is HomeViewModel.UiState.Loading -> {
                                            Box(
                                                contentAlignment = Alignment.Center
                                            ) {
                                                CircularProgressIndicator(
                                                    modifier = Modifier.width(15.dp),
                                                    color = MaterialTheme.colorScheme.background
                                                )
                                            }
                                        }
                                        is HomeViewModel.UiState.Error -> {}
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Row(
                Modifier
                    .padding(start = 8.dp, end = 8.dp)
            ) {
                Card(
                    Modifier
                        .padding(8.dp)
                        .width(160.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = Color.Black
                    )
                ) {
                    Spacer(modifier = Modifier.size(10.dp))
                    ElevatedCard(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.background
                        ),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            modifier = Modifier.padding(10.dp),
                            text = "FLOOR PRICE",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    Row(
                        Modifier
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Column(
                            Modifier
                                .padding(8.dp)
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            ElevatedCard(
                                colors = cardColors,
                                elevation = CardDefaults.cardElevation(20.dp)
                            ) {
                                Row(
                                    Modifier.padding(8.dp)
                                ) {
                                    when (uiState) {
                                        is HomeViewModel.UiState.Success -> {
                                            (uiState as HomeViewModel.UiState.Success)
                                                .data.let { stats ->
                                                    Text(
                                                        modifier = Modifier.padding(10.dp),
                                                        text = stats.floorPrice,
                                                        style = MaterialTheme.typography.titleSmall
                                                    )

                                                    Image(
                                                        modifier = Modifier
                                                            .align(Alignment.CenterVertically)
                                                            .size(16.dp),
                                                        imageVector = ImageVector
                                                            .vectorResource(id = R.drawable.egld),
                                                        contentDescription = "Coin EGLD logo"
                                                    )
                                                }
                                        }

                                        is HomeViewModel.UiState.Loading -> {
                                            Box(
                                                contentAlignment = Alignment.Center
                                            ) {
                                                CircularProgressIndicator(
                                                    modifier = Modifier.width(15.dp),
                                                    color = MaterialTheme.colorScheme.background
                                                )
                                            }
                                        }

                                        is HomeViewModel.UiState.Error -> {}
                                    }
                                }
                            }
                        }
                    }
                }

                Card(
                    Modifier
                        .padding(8.dp)
                        .width(160.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = Color.Black
                    )
                ) {
                    Spacer(modifier = Modifier.size(10.dp))
                    ElevatedCard(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.background
                        ),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            modifier = Modifier.padding(10.dp),
                            text = "TOTAL VOL.",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    Row(
                        Modifier
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Column(
                            Modifier
                                .padding(8.dp)
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            ElevatedCard(
                                colors = cardColors,
                                elevation = CardDefaults.cardElevation(20.dp)
                            ) {
                                Row(
                                    Modifier.padding(8.dp)
                                ) {
                                    when (uiState) {
                                        is HomeViewModel.UiState.Success -> {
                                            (uiState as HomeViewModel.UiState.Success)
                                                .data.let { stats ->
                                                    Text(
                                                        modifier = Modifier.padding(10.dp),
                                                        text = String.format(
                                                            Locale.getDefault(),
                                                            "%.2f",
                                                            stats.totalEgldVolume
                                                        ),
                                                        style = MaterialTheme.typography.titleSmall
                                                    )

                                                    Image(
                                                        modifier = Modifier
                                                            .align(Alignment.CenterVertically)
                                                            .size(16.dp),
                                                        imageVector = ImageVector
                                                            .vectorResource(id = R.drawable.egld),
                                                        contentDescription = "Coin EGLD logo"
                                                    )
                                                }
                                        }

                                        is HomeViewModel.UiState.Loading -> {
                                            Box(
                                                contentAlignment = Alignment.Center
                                            ) {
                                                CircularProgressIndicator(
                                                    modifier = Modifier.width(15.dp),
                                                    color = MaterialTheme.colorScheme.background
                                                )
                                            }
                                        }

                                        is HomeViewModel.UiState.Error -> {}
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.size(15.dp))

            ElevatedCard(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.background
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "THE LAST 10 SALES",
                    style = MaterialTheme.typography.titleSmall
                )
            }

            Spacer(modifier = Modifier.size(10.dp))

            when(uiStateSold) {
                is HomeViewModel.UiStateSold.Success -> {
                    (uiStateSold as HomeViewModel.UiStateSold.Success)
                        .data.let { listNftSold ->
                        LazyColumn(
                            modifier = Modifier
                                .height(600.dp),
                            content = {
                                items(listNftSold) { nftSold ->
                                    Spacer(modifier = Modifier.size(8.dp))
                                    ElevatedCard(
                                        modifier = Modifier.padding(
                                            start = 8.dp,
                                            end = 8.dp
                                        ),
                                        colors = cardColors,
                                        elevation = CardDefaults.cardElevation(20.dp)
                                    ) {
                                        Modifier
                                            .fillMaxWidth()
                                        Row(
                                            Modifier.animateItem(
                                                fadeInSpec = null,
                                                fadeOutSpec = null,
                                                placementSpec = tween(durationMillis = 250)
                                            ),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            GlideImage(
                                                model = nftSold.webpUrl,
                                                contentDescription = "Image Nft",
                                                modifier = Modifier
                                                    .size(50.dp)
                                            )
                                            Column(
                                                modifier = Modifier.padding(start = 6.dp)
                                            ) {
                                                Text(
                                                    text = nftSold.name.toString(),
                                                    style = MaterialTheme.typography.labelSmall
                                                )
                                                Text(
                                                    text = "Rank: " + nftSold.rank.toString(),
                                                    style = MaterialTheme.typography.labelSmall
                                                )
                                            }
                                            Column(
                                                modifier = Modifier.padding(start = 6.dp)
                                            ) {
                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    Text(
                                                        text = nftSold.egldValue.toString(),
                                                        style = MaterialTheme.typography.labelSmall
                                                    )
                                                    Icon(
                                                        modifier = Modifier.size(10.dp),
                                                        imageVector = ImageVector.vectorResource(
                                                            id = R.drawable.egld
                                                        ), contentDescription = "EGLD coin Logo"
                                                    )
                                                }
                                                Text(
                                                    text = "$" + nftSold.usdPrice.toString(),
                                                    style = MaterialTheme.typography.labelSmall
                                                )
                                            }
                                            Column(
                                                modifier = Modifier.padding(start = 6.dp)
                                            ) {
                                                Text(
                                                    text = "FROM",
                                                    style = MaterialTheme.typography.labelSmall
                                                )
                                                Text(
                                                    text = nftSold.sellerUsername.toString(),
                                                    style = MaterialTheme.typography.labelSmall
                                                )
                                            }
                                            Column(
                                                modifier = Modifier.padding(start = 6.dp)
                                            ) {
                                                Text(
                                                    text = "TO",
                                                    style = MaterialTheme.typography.labelSmall
                                                )
                                                Text(
                                                    text = nftSold.buyerUsername.toString(),
                                                    style = MaterialTheme.typography.labelSmall
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        )
                    }
                }

                is HomeViewModel.UiStateSold.Loading -> {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.width(15.dp),
                            color = MaterialTheme.colorScheme.background
                        )
                    }
                }

                is HomeViewModel.UiStateSold.Error -> {

                }
            }
        }
    }
}

