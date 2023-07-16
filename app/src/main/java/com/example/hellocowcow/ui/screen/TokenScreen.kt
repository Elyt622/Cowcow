package com.example.hellocowcow.ui.screen

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
import com.example.hellocowcow.ui.viewmodels.screen.TokenViewModel

@Composable
fun TokenScreen(
    viewModel: TokenViewModel
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()

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
                color = Color.White,
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
                        when (uiState) {
                            is TokenViewModel.UiState.Loading -> Box(
                                modifier = Modifier.width(60.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(color = Color.Black)
                            }

                            is TokenViewModel.UiState.Success -> Text(
                                text = (uiState as TokenViewModel.UiState.Success)
                                    .data.price.toString()
                                    .substring(0, 5),
                                fontSize = 35.sp
                            )

                            is TokenViewModel.UiState.Error ->
                                Toast.makeText(
                                    context,
                                    (uiState as TokenViewModel.UiState.Error).error,
                                    Toast.LENGTH_LONG)
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
                    text = "Total Ciculation Supply",
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
                        when (uiState) {
                            is TokenViewModel.UiState.Loading -> Box(
                                modifier = Modifier.width(60.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(color = Color.Black)
                            }

                            is TokenViewModel.UiState.Success -> Text(
                                text = (uiState as TokenViewModel.UiState.Success)
                                    .data.circulatingSupply.toString(),
                                fontSize = 35.sp
                            )

                            is TokenViewModel.UiState.Error -> Toast.makeText(
                                context,
                                (uiState as TokenViewModel.UiState.Error).error,
                                Toast.LENGTH_LONG)
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
                        when (uiState) {
                            is TokenViewModel.UiState.Loading -> Box(
                                modifier = Modifier.width(60.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(color = Color.Black)
                            }

                            is TokenViewModel.UiState.Success -> Text(
                                text = (uiState as TokenViewModel.UiState.Success).data.price.toString()
                                    .substring(0, 5),
                                fontSize = 35.sp
                            )

                            is TokenViewModel.UiState.Error -> Toast.makeText(
                                context,
                                (uiState as TokenViewModel.UiState.Error).error,
                                Toast.LENGTH_LONG)
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
        }
    }
}




