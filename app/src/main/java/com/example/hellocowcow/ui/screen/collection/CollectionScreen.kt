package com.example.hellocowcow.ui.screen.collection

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.hellocowcow.R
import com.example.hellocowcow.ui.viewmodels.screen.collection.CollectionViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CollectionScreen(
    viewModel: CollectionViewModel
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()

    val cardColors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.Black
    )

    Surface(
        modifier = Modifier
            .padding(bottom = 10.dp)
            .verticalScroll(rememberScrollState()),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            Modifier.padding(bottom = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Moove Forward.",
                fontSize = 22.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(top = 20.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Moove Together.",
                fontSize = 22.sp,
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.size(25.dp))

            GlideImage(
                modifier = Modifier
                    .scale(1.2F, 1.2F)
                    .fillMaxWidth(),
                model = "",
                contentDescription = "Cowcow Banner Image"
            ) {
                it.load(R.drawable.banner_thumbnail_h300)
            }

            Spacer(modifier = Modifier.size(30.dp))

            ElevatedCard(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.background
                )
            ) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "Cows",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(modifier = Modifier.size(8.dp))

            Row(
                Modifier
                    .align(Alignment.CenterHorizontally)
            ) {
                Column(
                    Modifier.padding(8.dp).weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Unbonding",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.size(10.dp))

                    ElevatedCard(
                        Modifier.align(Alignment.CenterHorizontally),
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
                                                text = collection.unbondingCows.toString(),
                                                style = MaterialTheme.typography.bodyLarge
                                            )
                                        }

                                is CollectionViewModel.UiState.Error ->
                                    (uiState as CollectionViewModel.UiState.Error)
                                        .error.let { err ->
                                            Toast.makeText(
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
                    Modifier.padding(8.dp).weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Staked",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyLarge
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
                                                text = collection.stakedCows.toString(),
                                                style = MaterialTheme.typography.bodyLarge
                                            )
                                        }

                                is CollectionViewModel.UiState.Error ->
                                    (uiState as CollectionViewModel.UiState.Error)
                                        .error.let { err ->
                                            Toast.makeText(
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
                    Modifier.padding(8.dp).weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Upgraded",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyLarge
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
                                                text = collection.totalUpgradedCows.toString(),
                                                style = MaterialTheme.typography.bodyLarge,
                                            )
                                        }

                                is CollectionViewModel.UiState.Error ->
                                    (uiState as CollectionViewModel.UiState.Error)
                                        .error.let { err ->
                                            Toast.makeText(
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
                Modifier
                    .align(Alignment.CenterHorizontally)
            ) {
                Column(
                    Modifier.padding(8.dp).weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Holders",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyLarge
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
                                                text = collection.holdersCows.toString(),
                                                style = MaterialTheme.typography.bodyLarge
                                            )
                                        }

                                is CollectionViewModel.UiState.Error ->
                                    (uiState as CollectionViewModel.UiState.Error)
                                        .error.let { err ->
                                            Toast.makeText(
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
                    Modifier.padding(8.dp).weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Listed",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyLarge
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
                                                text = collection.listedCows.toString(),
                                                style = MaterialTheme.typography.bodyLarge
                                            )
                                        }

                                is CollectionViewModel.UiState.Error ->
                                    (uiState as CollectionViewModel.UiState.Error)
                                        .error.let { err ->
                                            Toast.makeText(
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
                    Modifier.padding(8.dp).weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Floor Price",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyLarge
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
                                                text = collection.floorPriceCows.toString(),
                                                style = MaterialTheme.typography.bodyLarge
                                            )
                                        }

                                is CollectionViewModel.UiState.Error ->
                                    (uiState as CollectionViewModel.UiState.Error)
                                        .error.let { err ->
                                            Toast.makeText(
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

            Spacer(modifier = Modifier.size(30.dp))

            ElevatedCard(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.background
                )
            ) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "Tickets",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(modifier = Modifier.size(8.dp))

            Row(
                Modifier
                    .align(Alignment.CenterHorizontally)
            ) {
                Column(
                    Modifier.padding(8.dp).weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Ticket FP",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.size(10.dp))

                    ElevatedCard(
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
                                                text = collection.floorPriceTickets.toString(),
                                                style = MaterialTheme.typography.bodyLarge
                                            )
                                        }

                                is CollectionViewModel.UiState.Error ->
                                    (uiState as CollectionViewModel.UiState.Error)
                                        .error.let { err ->
                                            Toast.makeText(
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


                Column(
                    Modifier.padding(8.dp).weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Tickets Used",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.size(10.dp))

                    ElevatedCard(
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
                                                text = collection.ticketUsed?.Chapter4.toString(),
                                                style = MaterialTheme.typography.bodyLarge
                                            )
                                        }

                                is CollectionViewModel.UiState.Error ->
                                    (uiState as CollectionViewModel.UiState.Error)
                                        .error.let { err ->
                                            Toast.makeText(
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
                                    .vectorResource(id = R.drawable.ticket),
                                contentDescription = ""
                            )
                        }
                    }
                }
            }
        }
    }
}


