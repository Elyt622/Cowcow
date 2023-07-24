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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
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

    Surface(modifier = Modifier
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
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.primary
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
                            is TokenViewModel.UiStateToken.Loading -> Box(
                                modifier = Modifier.width(60.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(color = Color.Black)
                            }

                            is TokenViewModel.UiStateToken.Success -> Text(
                                text = (uiStateT as TokenViewModel.UiStateToken.Success)
                                    .data.price.toString()
                                    .substring(0, 5),
                                fontSize = 35.sp
                            )

                            is TokenViewModel.UiStateToken.Error ->
                                Toast.makeText(
                                    context,
                                    (uiStateT as TokenViewModel.UiStateToken.Error).error,
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                        }

                        Image(
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 4.dp)
                                .size(35.dp),
                            imageVector = ImageVector.vectorResource(id = R.drawable.usdc),
                            contentDescription = ""
                        )
                    }
                }

                Spacer(modifier = Modifier.size(20.dp))

                Divider(
                    color = MaterialTheme.colorScheme.primary,
                    thickness = 1.dp
                )
            }

            Column(
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Total Circulation Supply",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "* without unclaimed Moove",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.primary
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
                            is TokenViewModel.UiStateToken.Loading -> Box(
                                modifier = Modifier.width(60.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(color = Color.Black)
                            }

                            is TokenViewModel.UiStateToken.Success -> Text(
                                text = (uiStateT as TokenViewModel.UiStateToken.Success)
                                    .data.circulatingSupply.toString(),
                                fontSize = 35.sp
                            )

                            is TokenViewModel.UiStateToken.Error -> Toast.makeText(
                                context,
                                (uiStateT as TokenViewModel.UiStateToken.Error).error,
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }

                        Image(
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 4.dp)
                                .size(35.dp),
                            imageVector = ImageVector.vectorResource(id = R.drawable.moovelogo),
                            contentDescription = ""
                        )
                    }
                }

                Spacer(modifier = Modifier.size(20.dp))

                Divider(
                    color = MaterialTheme.colorScheme.primary,
                    thickness = 1.dp
                )
            }


            Column(
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Unclaimed Moove",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.primary
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
                            is TokenViewModel.UiStateReward.Loading -> Box(
                                modifier = Modifier.width(60.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(color = Color.Black)
                            }

                            is TokenViewModel.UiStateReward.Success -> Text(
                                text = (uiStateR as TokenViewModel.UiStateReward.Success).data.unclaimedMoove,
                                fontSize = 35.sp
                            )

                            is TokenViewModel.UiStateReward.Error -> Toast.makeText(
                                context,
                                (uiStateR as TokenViewModel.UiStateReward.Error).error,
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }

                        Image(
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 4.dp)
                                .size(35.dp),
                            imageVector = ImageVector.vectorResource(id = R.drawable.moovelogo),
                            contentDescription = ""
                        )
                    }
                }

                Spacer(modifier = Modifier.size(20.dp))

                Divider(
                    color = MaterialTheme.colorScheme.primary,
                    thickness = 1.dp
                )
            }

            Column(
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "MarketCap",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.primary
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
                            is TokenViewModel.UiStateToken.Loading -> Box(
                                modifier = Modifier.width(60.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(color = Color.Black)
                            }

                            is TokenViewModel.UiStateToken.Success -> Text(
                                text = (uiStateT as TokenViewModel.UiStateToken.Success).data.marketCap?.roundToInt()
                                    .toString() + " $",
                                fontSize = 35.sp
                            )

                            is TokenViewModel.UiStateToken.Error -> Toast.makeText(
                                context,
                                (uiStateT as TokenViewModel.UiStateToken.Error).error,
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }
                    }
                }

                Spacer(modifier = Modifier.size(20.dp))

                Divider(
                    color = MaterialTheme.colorScheme.primary,
                    thickness = 1.dp
                )
            }

            Column(
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Holders",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.primary
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
                            is TokenViewModel.UiStateToken.Loading -> Box(
                                modifier = Modifier.width(60.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(color = Color.Black)
                            }

                            is TokenViewModel.UiStateToken.Success -> Text(
                                text = (uiStateT as TokenViewModel.UiStateToken.Success).data.accounts.toString(),
                                fontSize = 35.sp
                            )

                            is TokenViewModel.UiStateToken.Error -> Toast.makeText(
                                context,
                                (uiStateT as TokenViewModel.UiStateToken.Error).error,
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }
                    }
                }

                Spacer(modifier = Modifier.size(20.dp))

                Divider(
                    color = MaterialTheme.colorScheme.primary,
                    thickness = 1.dp
                )
            }

            Column(
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Transactions",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.primary
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
                            is TokenViewModel.UiStateToken.Loading -> Box(
                                modifier = Modifier.width(60.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(color = Color.Black)
                            }

                            is TokenViewModel.UiStateToken.Success -> Text(
                                text = (uiStateT as TokenViewModel.UiStateToken.Success).data.transactions.toString(),
                                fontSize = 35.sp
                            )

                            is TokenViewModel.UiStateToken.Error -> Toast.makeText(
                                context,
                                (uiStateT as TokenViewModel.UiStateToken.Error).error,
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }
                    }
                }

                Spacer(modifier = Modifier.size(20.dp))

                Divider(
                    color = MaterialTheme.colorScheme.primary,
                    thickness = 1.dp
                )
            }
        }
    }
}




