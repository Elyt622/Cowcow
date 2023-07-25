package com.example.hellocowcow.ui.screen.token

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hellocowcow.R
import com.example.hellocowcow.ui.viewmodels.screen.token.TokenViewModel
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
            .padding(bottom = 10.dp)
            .verticalScroll(rememberScrollState()),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            Modifier.padding(bottom = 50.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Moove Forward.",
                fontSize = 28.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Moove Together.",
                fontSize = 28.sp,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.size(10.dp))

            Column(
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Moove Price",
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
                                            text = token.price.toString()
                                                .substring(0, 5),
                                            style = MaterialTheme.typography.bodyLarge
                                        )
                                    }

                            is TokenViewModel.UiStateToken.Error ->
                                (uiStateT as TokenViewModel.UiStateToken.Error)
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
                            imageVector = ImageVector.vectorResource(id = R.drawable.usdc),
                            contentDescription = ""
                        )
                    }
                }

                Spacer(modifier = Modifier.size(20.dp))

                Text(
                    text = "Total Circulation Supply",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = "* without unclaimed Moove",
                    color = MaterialTheme.colorScheme.primary,
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
                                            text = token.circulatingSupply.toString(),
                                            style = MaterialTheme.typography.bodyLarge
                                        )
                                    }

                            is TokenViewModel.UiStateToken.Error ->
                                (uiStateT as TokenViewModel.UiStateToken.Error)
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
                                .vectorResource(id = R.drawable.moovelogo),
                            contentDescription = ""
                        )
                    }
                }

                Spacer(modifier = Modifier.size(20.dp))

                Text(
                    text = "Unclaimed Moove",
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
                                .vectorResource(id = R.drawable.moovelogo),
                            contentDescription = ""
                        )
                    }
                }

                Spacer(modifier = Modifier.size(20.dp))

                Text(
                    text = "MarketCap",
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
                                        Toast.makeText(
                                            context,
                                            err,
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                        }
                    }
                }

                Spacer(modifier = Modifier.size(20.dp))

                Text(
                    text = "Holders",
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
                                        Toast.makeText(
                                            context,
                                            err,
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                        }
                    }
                }

                Spacer(modifier = Modifier.size(20.dp))

                Text(
                    text = "Transactions",
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
                                        Toast.makeText(
                                            context,
                                            err,
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                        }
                    }
                }
                Spacer(modifier = Modifier.size(20.dp))
            }
        }
    }
}




