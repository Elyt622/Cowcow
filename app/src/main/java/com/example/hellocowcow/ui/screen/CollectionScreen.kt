package com.example.hellocowcow.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hellocowcow.R
import com.example.hellocowcow.ui.composables.StatsCard
import com.example.hellocowcow.ui.viewmodels.CollectionViewModel

@Composable
fun CollectionScreen(
    viewModel: CollectionViewModel
) {

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
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.size(10.dp))
/*
            StatsCard(
                modifier = Modifier
                    .padding(start = 8.dp),
                colors = cardColors,
                "Cows Unbonding",
                "Yesterday : 110 (-39.09%)",
                "67",
                null
            )

            StatsCard(
                modifier = Modifier
                    .padding(start = 8.dp),
                colors = cardColors,
                "Cows Staked",
                "Yesterday : 9106 (1.44%)",
                "9237",
                null

            )

            StatsCard(
                modifier = Modifier
                    .padding(start = 8.dp),
                colors = cardColors,
                "Cows Upgraded",
                "Yesterday : 4893 (7.01%)",
                "4893",
                null
            )

            StatsCard(
                modifier = Modifier
                    .padding(start = 8.dp),
                colors = cardColors,
                "Cow Holders",
                "Yesterday : 1523 (-0.66%)",
                "1513",
                null
            )

            StatsCard(
                modifier = Modifier
                    .padding(start = 8.dp),
                colors = cardColors,
                "Cows Listed",
                "Yesterday : 247 (-22.27%)",
                "192",
                null
            )

            StatsCard(
                modifier = Modifier
                    .padding(start = 8.dp),
                colors = cardColors,
                "Cow Floor Price",
                "Yesterday : 6.78 (11.36%)",
                "7.55",
                ImageVector.vectorResource(id = R.drawable.egld)
            )

            StatsCard(
                modifier = Modifier
                    .padding(start = 8.dp),
                colors = cardColors,
                "Updated Cows Floor Price",
                "Yesterday : 10.5 (9.52%)",
                "11.5",
                ImageVector.vectorResource(id = R.drawable.egld)
            )

            StatsCard(
                modifier = Modifier
                    .padding(start = 8.dp),
                colors = cardColors,
                "Ticket Floor Price",
                "Yesterday : 7.25 (7.59%)",
                "7.25",
                ImageVector.vectorResource(id = R.drawable.egld)
            )

            StatsCard(
                modifier = Modifier
                    .padding(start = 8.dp),
                colors = cardColors,
                "Ticket Used",
                "Yesterday : 398 (2.51%)",
                "408",
                ImageVector.vectorResource(id = R.drawable.ticket)

            )*/
        }
    }
}

