package com.example.hellocowcow.ui.screen.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
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

@OptIn(ExperimentalGlideComposeApi::class)
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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

            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.background
                    ),
                    onClick = {
                        navController.navigate(ItemNav.Stats.route)
                    }
                ) {
                    Text(
                        text = "Stats",
                        style = MaterialTheme.typography.titleSmall
                    )
                }

                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.background
                    ),
                    onClick = {
                        navController.navigate(ItemNav.Profile.route)
                    }
                ) {
                    Text(
                        text = "Profile",
                        style = MaterialTheme.typography.titleSmall
                    )
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
                            text = "Moove Price",
                            style = MaterialTheme.typography.titleSmall
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
                            text = "MarketCap",
                            style = MaterialTheme.typography.titleSmall
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
                            text = "Cows Staked",
                            style = MaterialTheme.typography.titleSmall
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
                            text = "Cows Listed",
                            style = MaterialTheme.typography.titleSmall
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
                            text = "Floor Price",
                            style = MaterialTheme.typography.titleSmall
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
                            text = "Total Volume",
                            style = MaterialTheme.typography.titleSmall
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
        }
    }
}

