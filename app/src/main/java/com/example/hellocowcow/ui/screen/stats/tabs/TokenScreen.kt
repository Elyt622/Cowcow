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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.hellocowcow.R
import com.example.hellocowcow.ui.viewmodels.screen.stats.TokenViewModel
import es.dmoral.toasty.Toasty
import kotlin.math.roundToInt

@Composable
fun TokenScreen(
    viewModel: TokenViewModel
) {
    val context = LocalContext.current

    val uiStateT by viewModel.uiStateT.collectAsState()
    val uiStateR by viewModel.uiStateR.collectAsState()

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
                        contentColor = MaterialTheme.colorScheme.background,
                    ),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = "Moove",
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
                            text = "Price",
                            style = MaterialTheme.typography.labelMedium
                        )

                        Spacer(modifier = Modifier.size(10.dp))

                        ElevatedCard(
                            colors = cardColors,
                            elevation = CardDefaults.cardElevation(20.dp)
                        ) {
                            Row(
                                Modifier.padding(8.dp)
                            ) {
                                when (uiStateT) {
                                    is TokenViewModel.UiStateToken.Loading ->
                                        Box(
                                            modifier = Modifier.size(16.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            CircularProgressIndicator(color = Color.Black)
                                        }

                                    is TokenViewModel.UiStateToken.Success ->
                                        (uiStateT as TokenViewModel.UiStateToken.Success)
                                            .data.let { token ->
                                                Text(
                                                    text = String.format(
                                                        "%.3f",
                                                        token.price
                                                    ),
                                                    style = MaterialTheme.typography.bodyLarge
                                                )
                                            }

                                    is TokenViewModel.UiStateToken.Error ->
                                        (uiStateT as TokenViewModel.UiStateToken.Error)
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
                                    imageVector = ImageVector.vectorResource(id = R.drawable.usdc),
                                    contentDescription = ""
                                )
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
                            text = "Unclaimed",
                            style = MaterialTheme.typography.labelMedium
                        )

                        Spacer(modifier = Modifier.size(10.dp))

                        ElevatedCard(
                            colors = cardColors,
                            elevation = CardDefaults.cardElevation(20.dp)
                        ) {
                            Row(
                                Modifier.padding(8.dp)
                            ) {
                                when (uiStateR) {
                                    is TokenViewModel.UiStateReward.Loading ->
                                        Box(
                                            modifier = Modifier.size(16.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            CircularProgressIndicator(color = Color.Black)
                                        }

                                    is TokenViewModel.UiStateReward.Success ->
                                        (uiStateR as TokenViewModel.UiStateReward.Success)
                                            .data.let { token ->
                                                Text(
                                                    text = token.unclaimedMoove,
                                                    style = MaterialTheme.typography.bodyLarge
                                                )
                                            }

                                    is TokenViewModel.UiStateReward.Error ->
                                        (uiStateR as TokenViewModel.UiStateReward.Error)
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
                                        .vectorResource(id = R.drawable.moovelogo),
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
                            text = "Circulating Supply",
                            style = MaterialTheme.typography.labelMedium
                        )

                        Text(
                            text = "* without unclaimed",
                            style = MaterialTheme.typography.labelSmall
                        )

                        Spacer(modifier = Modifier.size(10.dp))

                        ElevatedCard(
                            colors = cardColors,
                            elevation = CardDefaults.cardElevation(20.dp)
                        ) {
                            Row(
                                Modifier.padding(8.dp)
                            ) {
                                when (uiStateT) {
                                    is TokenViewModel.UiStateToken.Loading ->
                                        Box(
                                            modifier = Modifier.size(16.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            CircularProgressIndicator(color = Color.Black)
                                        }

                                    is TokenViewModel.UiStateToken.Success ->
                                        (uiStateT as TokenViewModel.UiStateToken.Success)
                                            .data.let { token ->
                                                Text(
                                                    text = token.circulatingSupply.toString(),
                                                    style = MaterialTheme.typography.bodyLarge
                                                )
                                            }

                                    is TokenViewModel.UiStateToken.Error ->
                                        (uiStateT as TokenViewModel.UiStateToken.Error)
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
                                        .vectorResource(id = R.drawable.moovelogo),
                                    contentDescription = ""
                                )
                            }
                        }
                    }
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
                        Text(
                            text = "MarketCap",
                            style = MaterialTheme.typography.labelMedium
                        )

                        Spacer(modifier = Modifier.size(10.dp))

                        ElevatedCard(
                            colors = cardColors,
                            elevation = CardDefaults.cardElevation(20.dp)
                        ) {
                            Row(
                                Modifier.padding(8.dp)
                            ) {
                                when (uiStateT) {
                                    is TokenViewModel.UiStateToken.Loading ->
                                        Box(
                                            modifier = Modifier.size(16.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            CircularProgressIndicator(color = Color.Black)
                                        }

                                    is TokenViewModel.UiStateToken.Success ->
                                        (uiStateT as TokenViewModel.UiStateToken.Success)
                                            .data.let { token ->
                                                Text(
                                                    text = token.marketCap
                                                        ?.roundToInt()
                                                        .toString() + " $",
                                                    style = MaterialTheme.typography.bodyLarge
                                                )
                                            }

                                    is TokenViewModel.UiStateToken.Error ->
                                        (uiStateT as TokenViewModel.UiStateToken.Error)
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
                            text = "Holders",
                            style = MaterialTheme.typography.labelMedium
                        )

                        Spacer(modifier = Modifier.size(10.dp))

                        ElevatedCard(
                            colors = cardColors,
                            elevation = CardDefaults.cardElevation(20.dp)
                        ) {
                            Row(
                                Modifier.padding(8.dp)
                            ) {
                                when (uiStateT) {
                                    is TokenViewModel.UiStateToken.Loading ->
                                        Box(
                                            modifier = Modifier.size(16.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            CircularProgressIndicator(color = Color.Black)
                                        }

                                    is TokenViewModel.UiStateToken.Success ->
                                        (uiStateT as TokenViewModel.UiStateToken.Success)
                                            .data.let { token ->
                                                Text(
                                                    text = token.accounts.toString(),
                                                    style = MaterialTheme.typography.bodyLarge
                                                )
                                            }

                                    is TokenViewModel.UiStateToken.Error ->
                                        (uiStateT as TokenViewModel.UiStateToken.Error)
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
                            text = "Transactions",
                            style = MaterialTheme.typography.labelMedium
                        )

                        Spacer(modifier = Modifier.size(10.dp))

                        ElevatedCard(
                            colors = cardColors,
                            elevation = CardDefaults.cardElevation(20.dp)
                        ) {
                            Row(
                                Modifier.padding(8.dp)
                            ) {
                                when (uiStateT) {
                                    is TokenViewModel.UiStateToken.Loading ->
                                        Box(
                                            modifier = Modifier.size(16.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            CircularProgressIndicator(color = Color.Black)
                                        }

                                    is TokenViewModel.UiStateToken.Success ->
                                        (uiStateT as TokenViewModel.UiStateToken.Success)
                                            .data.let { token ->
                                                Text(
                                                    text = token.transactions.toString(),
                                                    style = MaterialTheme.typography.bodyLarge
                                                )
                                            }

                                    is TokenViewModel.UiStateToken.Error ->
                                        (uiStateT as TokenViewModel.UiStateToken.Error)
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



