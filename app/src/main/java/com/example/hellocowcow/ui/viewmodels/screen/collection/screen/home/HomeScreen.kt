package com.example.hellocowcow.ui.viewmodels.screen.collection.screen.home

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.hellocowcow.R
import com.example.hellocowcow.app.module.InfoActivity
import com.example.hellocowcow.ui.viewmodels.screen.home.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {

    val context = LocalContext.current

    Column (
        Modifier
            .padding(bottom = 70.dp)
            .verticalScroll(
                rememberScrollState()
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.thecity),
            contentDescription = "The City",
            Modifier.clickable {
                    val intent = Intent(
                        context,
                        InfoActivity::class.java
                    )
                    intent.putExtra("IMAGE", "THE_CITY")
                    context.startActivity(intent)
                }
        )

        Spacer(modifier = Modifier.size(5.dp))

        Row {
            Image(
                painter = painterResource(id = R.drawable.cowcow),
                contentDescription = "CowCow",
                Modifier
                    .fillMaxWidth(0.66F)
                    .clickable {
                        val intent = Intent(
                            context,
                            InfoActivity::class.java
                        )
                        intent.putExtra("IMAGE", "COWCOW")
                        context.startActivity(intent)
                    }
            )
            Spacer(modifier = Modifier.size(5.dp))
            Image(
                painter = painterResource(id = R.drawable.raffle),
                contentDescription = "Raffle",
                Modifier.clickable {
                    val intent = Intent(
                        context,
                        InfoActivity::class.java
                    )
                    intent.putExtra("IMAGE", "RAFFLE")
                    context.startActivity(intent)
                }
            )
        }

        Spacer(modifier = Modifier.size(5.dp))

        Image(
            painter = painterResource(id = R.drawable.urban_plan),
            contentDescription = "Urban Plan",
            Modifier.clickable {
                val intent = Intent(
                    context,
                    InfoActivity::class.java
                )
                intent.putExtra("IMAGE", "URBAN_PLAN")
                context.startActivity(intent)
            }
        )

        Spacer(modifier = Modifier.size(5.dp))

        Row (Modifier.height(115.dp)){
            Image(
                painter = painterResource(id = R.drawable.staking),
                contentDescription = "Staking",
                Modifier.clickable {
                    val intent = Intent(
                        context,
                        InfoActivity::class.java
                    )
                    intent.putExtra("IMAGE", "STAKING")
                    context.startActivity(intent)
                }
            )

            Spacer(modifier = Modifier.size(5.dp))

            Image(
                painter = painterResource(id = R.drawable.manifesto),
                contentDescription = "Manifesto",
                Modifier
                    .fillMaxWidth(1.1F)
                    .clickable {
                    val intent = Intent(
                        context,
                        InfoActivity::class.java
                    )
                    intent.putExtra("IMAGE", "MANIFESTO")
                    context.startActivity(intent)
                }
            )
        }

        Spacer(modifier = Modifier.size(5.dp))

        Image(
            painter = painterResource(id = R.drawable.team),
            contentDescription = "Team",
            Modifier.clickable {
                val intent = Intent(
                    context,
                    InfoActivity::class.java
                )
                intent.putExtra("IMAGE", "TEAM")
                context.startActivity(intent)
            }
        )

        Spacer(modifier = Modifier.size(5.dp))

        Row {
            Image(
                painter = painterResource(id = R.drawable.origins),
                contentDescription = "Origins",
                Modifier.fillMaxWidth(0.66F)
                        .clickable {
                    val intent = Intent(
                        context,
                        InfoActivity::class.java
                    )
                    intent.putExtra("IMAGE", "ORIGINS")
                    context.startActivity(intent)
                })

            Spacer(modifier = Modifier.size(5.dp))

            Image(
                painter = painterResource(id = R.drawable.twenty_three),
                contentDescription = "23",
                Modifier.clickable {
                    val intent = Intent(
                        context,
                        InfoActivity::class.java
                    )
                    intent.putExtra("IMAGE", "TWENTY_THREE")
                    context.startActivity(intent)
                }
            )
        }
    }
}

