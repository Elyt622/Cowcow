package com.example.hellocowcow.ui.screen.stats.tabs

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.hellocowcow.R
import com.example.hellocowcow.ui.viewmodels.screen.stats.CollectionViewModel
import es.dmoral.toasty.Toasty

@Composable
fun CollectionScreen(
    collectionViewModel: CollectionViewModel,
) {
    val context = LocalContext.current

    val uiState by collectionViewModel.uiState.collectAsState()

    val cardColors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.Black
    )

    Surface(
        modifier = Modifier
            .padding(bottom = 40.dp, top = 10.dp)
            .verticalScroll(rememberScrollState()),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally),
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
                        text = "Cow",
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                Row(
                    Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Column(
                        Modifier
                            .padding(8.dp)
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Staked",
                            style = MaterialTheme.typography.labelMedium
                        )

                        Spacer(modifier = Modifier.size(10.dp))

                        ElevatedCard(
                            colors = cardColors,
                            elevation = CardDefaults.cardElevation(20.dp)
                        ) {
                            Box(
                                Modifier.padding(8.dp)
                            ) {
                                when (uiState) {
                                    is CollectionViewModel.UiState.Loading ->
                                        Box(
                                            modifier = Modifier.size(16.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            CircularProgressIndicator(color = Color.Black)
                                        }

                                    is CollectionViewModel.UiState.Success ->
                                        (uiState as CollectionViewModel.UiState.Success)
                                            .data.let { collection ->
                                                Text(
                                                    text = collection.stakedCount.toString(),
                                                    style = MaterialTheme.typography.bodyLarge
                                                )
                                            }

                                    is CollectionViewModel.UiState.Error ->
                                        (uiState as CollectionViewModel.UiState.Error)
                                            .error.let { err ->
                                                Toasty.error(
                                                    context,
                                                    err,
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                }
                            }
                        }
                    }

                    Column(
                        Modifier
                            .padding(8.dp)
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Upgraded",
                            style = MaterialTheme.typography.labelMedium
                        )

                        Spacer(modifier = Modifier.size(10.dp))

                        ElevatedCard(
                            colors = cardColors,
                            elevation = CardDefaults.cardElevation(20.dp)
                        ) {
                            Box(
                                Modifier.padding(8.dp),
                            ) {
                                when (uiState) {
                                    is CollectionViewModel.UiState.Loading ->
                                        Box(
                                            modifier = Modifier.size(16.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            CircularProgressIndicator(color = Color.Black)
                                        }

                                    is CollectionViewModel.UiState.Success ->
                                        (uiState as CollectionViewModel.UiState.Success)
                                            .data.let { collection ->
                                                Text(
                                                    text = collection.totalUpgradedCount.toString(),
                                                    style = MaterialTheme.typography.bodyLarge,
                                                )
                                            }

                                    is CollectionViewModel.UiState.Error ->
                                        (uiState as CollectionViewModel.UiState.Error)
                                            .error.let { err ->
                                                Toasty.error(
                                                    context,
                                                    err,
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                }
                            }
                        }
                    }
                }

                Row(
                    Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Column(
                        Modifier
                            .padding(8.dp)
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Holders",
                            style = MaterialTheme.typography.labelMedium
                        )

                        Spacer(modifier = Modifier.size(10.dp))

                        ElevatedCard(
                            Modifier.align(Alignment.CenterHorizontally),
                            colors = cardColors,
                            elevation = CardDefaults.cardElevation(20.dp)
                        ) {
                            Row(
                                Modifier.padding(8.dp)
                            ) {
                                when (uiState) {
                                    is CollectionViewModel.UiState.Loading ->
                                        Box(
                                            modifier = Modifier.size(16.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            CircularProgressIndicator(color = Color.Black)
                                        }

                                    is CollectionViewModel.UiState.Success ->
                                        (uiState as CollectionViewModel.UiState.Success)
                                            .data.let { collection ->
                                                Text(
                                                    text = collection.holdersCount.toString(),
                                                    style = MaterialTheme.typography.bodyLarge
                                                )
                                            }

                                    is CollectionViewModel.UiState.Error ->
                                        (uiState as CollectionViewModel.UiState.Error)
                                            .error.let { err ->
                                                Toasty.error(
                                                    context,
                                                    err,
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                }
                            }
                        }
                    }

                    Column(
                        Modifier
                            .padding(8.dp)
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Listed",
                            style = MaterialTheme.typography.labelMedium
                        )

                        Spacer(modifier = Modifier.size(10.dp))

                        ElevatedCard(
                            Modifier.align(Alignment.CenterHorizontally),
                            colors = cardColors,
                            elevation = CardDefaults.cardElevation(20.dp)
                        ) {
                            Row(
                                Modifier.padding(8.dp)
                            ) {
                                when (uiState) {
                                    is CollectionViewModel.UiState.Loading ->
                                        Box(
                                            modifier = Modifier.size(16.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            CircularProgressIndicator(color = Color.Black)
                                        }

                                    is CollectionViewModel.UiState.Success ->
                                        (uiState as CollectionViewModel.UiState.Success)
                                            .data.let { collection ->
                                                Text(
                                                    text = collection.listedCount.toString(),
                                                    style = MaterialTheme.typography.bodyLarge
                                                )
                                            }

                                    is CollectionViewModel.UiState.Error ->
                                        (uiState as CollectionViewModel.UiState.Error)
                                            .error.let { err ->
                                                Toasty.error(
                                                    context,
                                                    err,
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                }
                            }
                        }
                    }

                    Column(
                        Modifier
                            .padding(8.dp)
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Floor Price",
                            style = MaterialTheme.typography.labelMedium
                        )

                        Spacer(modifier = Modifier.size(10.dp))

                        ElevatedCard(
                            Modifier.align(Alignment.CenterHorizontally),
                            colors = cardColors,
                            elevation = CardDefaults.cardElevation(20.dp)
                        ) {
                            Row(
                                Modifier.padding(8.dp)
                            ) {
                                when (uiState) {
                                    is CollectionViewModel.UiState.Loading ->
                                        Box(
                                            modifier = Modifier.size(16.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            CircularProgressIndicator(color = Color.Black)
                                        }

                                    is CollectionViewModel.UiState.Success ->
                                        (uiState as CollectionViewModel.UiState.Success)
                                            .data.let { collection ->
                                                Text(
                                                    text = collection.floorPrice.toString(),
                                                    style = MaterialTheme.typography.bodyLarge
                                                )
                                            }

                                    is CollectionViewModel.UiState.Error ->
                                        (uiState as CollectionViewModel.UiState.Error)
                                            .error.let { err ->
                                                Toasty.error(
                                                    context,
                                                    err,
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                }

                                Image(
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                        .padding(start = 4.dp)
                                        .size(16.dp),
                                    imageVector = ImageVector
                                        .vectorResource(id = R.drawable.egld),
                                    contentDescription = ""
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.size(12.dp))

                Row(
                    Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Column(
                        Modifier
                            .padding(8.dp)
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "ATH",
                            style = MaterialTheme.typography.labelMedium
                        )

                        Spacer(modifier = Modifier.size(10.dp))

                        ElevatedCard(
                            Modifier.align(Alignment.CenterHorizontally),
                            colors = cardColors,
                            elevation = CardDefaults.cardElevation(20.dp)
                        ) {
                            Row(
                                Modifier.padding(8.dp)
                            ) {
                                when (uiState) {
                                    is CollectionViewModel.UiState.Loading ->
                                        Box(
                                            modifier = Modifier.size(16.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            CircularProgressIndicator(color = Color.Black)
                                        }

                                    is CollectionViewModel.UiState.Success ->
                                        (uiState as CollectionViewModel.UiState.Success)
                                            .data.let { collection ->
                                                Text(
                                                    text = collection.athEgldPrice.toString(),
                                                    style = MaterialTheme.typography.bodyLarge
                                                )
                                            }

                                    is CollectionViewModel.UiState.Error ->
                                        (uiState as CollectionViewModel.UiState.Error)
                                            .error.let { err ->
                                                Toasty.error(
                                                    context,
                                                    err,
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                }
                            }
                        }
                    }

                    Column(
                        Modifier
                            .padding(8.dp)
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Total trades",
                            style = MaterialTheme.typography.labelMedium
                        )

                        Spacer(modifier = Modifier.size(10.dp))

                        ElevatedCard(
                            Modifier.align(Alignment.CenterHorizontally),
                            colors = cardColors,
                            elevation = CardDefaults.cardElevation(20.dp)
                        ) {
                            Row(
                                Modifier.padding(8.dp)
                            ) {
                                when (uiState) {
                                    is CollectionViewModel.UiState.Loading ->
                                        Box(
                                            modifier = Modifier.size(16.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            CircularProgressIndicator(color = Color.Black)
                                        }

                                    is CollectionViewModel.UiState.Success ->
                                        (uiState as CollectionViewModel.UiState.Success)
                                            .data.let { collection ->
                                                Text(
                                                    text = collection.totalTrades.toString(),
                                                    style = MaterialTheme.typography.bodyLarge
                                                )
                                            }

                                    is CollectionViewModel.UiState.Error ->
                                        (uiState as CollectionViewModel.UiState.Error)
                                            .error.let { err ->
                                                Toasty.error(
                                                    context,
                                                    err,
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                }
                            }
                        }
                    }

                    Column(
                        Modifier
                            .padding(8.dp)
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Follow Account",
                            style = MaterialTheme.typography.labelMedium
                        )

                        Spacer(modifier = Modifier.size(10.dp))

                        ElevatedCard(
                            Modifier.align(Alignment.CenterHorizontally),
                            colors = cardColors,
                            elevation = CardDefaults.cardElevation(20.dp)
                        ) {
                            Row(
                                Modifier.padding(8.dp)
                            ) {
                                when (uiState) {
                                    is CollectionViewModel.UiState.Loading ->
                                        Box(
                                            modifier = Modifier.size(16.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            CircularProgressIndicator(color = Color.Black)
                                        }

                                    is CollectionViewModel.UiState.Success ->
                                        (uiState as CollectionViewModel.UiState.Success)
                                            .data.let { collection ->
                                                Text(
                                                    text = collection.followAccountsCount.toString(),
                                                    style = MaterialTheme.typography.bodyLarge
                                                )
                                            }

                                    is CollectionViewModel.UiState.Error ->
                                        (uiState as CollectionViewModel.UiState.Error)
                                            .error.let { err ->
                                                Toasty.error(
                                                    context,
                                                    err,
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                }

                                Icon(
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                        .padding(start = 4.dp)
                                        .size(16.dp),
                                    imageVector = Icons.Filled.Person,
                                    contentDescription = ""
                                )
                            }
                        }
                    }
                }

                Row(
                    Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Column(
                        Modifier
                            .padding(8.dp)
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Day EGLD Volume",
                            style = MaterialTheme.typography.labelMedium
                        )

                        Spacer(modifier = Modifier.size(10.dp))

                        ElevatedCard(
                            colors = cardColors,
                            elevation = CardDefaults.cardElevation(20.dp)
                        ) {
                            Box(
                                Modifier.padding(8.dp)
                            ) {
                                when (uiState) {
                                    is CollectionViewModel.UiState.Loading ->
                                        Box(
                                            modifier = Modifier.size(16.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            CircularProgressIndicator(color = Color.Black)
                                        }

                                    is CollectionViewModel.UiState.Success ->
                                        (uiState as CollectionViewModel.UiState.Success)
                                            .data.let { collection ->
                                                Text(
                                                    text = collection.dayEgldVolume.toString(),
                                                    style = MaterialTheme.typography.bodyLarge
                                                )
                                            }

                                    is CollectionViewModel.UiState.Error ->
                                        (uiState as CollectionViewModel.UiState.Error)
                                            .error.let { err ->
                                                Toasty.error(
                                                    context,
                                                    err,
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                }
                            }
                        }
                    }

                    Column(
                        Modifier
                            .padding(8.dp)
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Week EGLD Volume",
                            style = MaterialTheme.typography.labelMedium
                        )

                        Spacer(modifier = Modifier.size(10.dp))

                        ElevatedCard(
                            colors = cardColors,
                            elevation = CardDefaults.cardElevation(20.dp)
                        ) {
                            Box(
                                Modifier.padding(8.dp),
                            ) {
                                when (uiState) {
                                    is CollectionViewModel.UiState.Loading ->
                                        Box(
                                            modifier = Modifier.size(16.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            CircularProgressIndicator(color = Color.Black)
                                        }

                                    is CollectionViewModel.UiState.Success ->
                                        (uiState as CollectionViewModel.UiState.Success)
                                            .data.let { collection ->
                                                Text(
                                                    text = collection.weekEgldVolume.toString(),
                                                    style = MaterialTheme.typography.bodyLarge,
                                                )
                                            }

                                    is CollectionViewModel.UiState.Error ->
                                        (uiState as CollectionViewModel.UiState.Error)
                                            .error.let { err ->
                                                Toasty.error(
                                                    context,
                                                    err,
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                }
                            }
                        }
                    }
                }

                Row(
                    Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Column(
                        Modifier
                            .padding(8.dp)
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Total EGLD Volume",
                            style = MaterialTheme.typography.labelMedium
                        )

                        Spacer(modifier = Modifier.size(10.dp))

                        ElevatedCard(
                            colors = cardColors,
                            elevation = CardDefaults.cardElevation(20.dp)
                        ) {
                            Box(
                                Modifier.padding(8.dp)
                            ) {
                                when (uiState) {
                                    is CollectionViewModel.UiState.Loading ->
                                        Box(
                                            modifier = Modifier.size(16.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            CircularProgressIndicator(color = Color.Black)
                                        }

                                    is CollectionViewModel.UiState.Success ->
                                        (uiState as CollectionViewModel.UiState.Success)
                                            .data.let { collection ->
                                                Text(
                                                    text = collection.totalEgldVolume.toString(),
                                                    style = MaterialTheme.typography.bodyLarge
                                                )
                                            }

                                    is CollectionViewModel.UiState.Error ->
                                        (uiState as CollectionViewModel.UiState.Error)
                                            .error.let { err ->
                                                Toasty.error(
                                                    context,
                                                    err,
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.size(12.dp))
            }
        }
    }
}


