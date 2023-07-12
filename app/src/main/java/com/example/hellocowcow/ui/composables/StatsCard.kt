package com.example.hellocowcow.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hellocowcow.R
import com.example.hellocowcow.ui.viewmodels.TokenViewModel
import com.google.android.material.progressindicator.CircularProgressIndicator

@Composable
fun StatsCard (
    uiState: TokenViewModel.UiState,
    modifier: Modifier,
    colors: CardColors,
    title: String,
    subtitle: String,
    stat: String,
    imageVector: ImageVector?
) {
    Column(
        Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.size(10.dp))

        ElevatedCard(
            modifier = modifier,
            colors = colors,
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
                        text = uiState.data.price.toString().substring(0,5),
                        fontSize = 35.sp
                    )
                    is TokenViewModel.UiState.Error -> ""
                }

                imageVector?.let {
                    Image(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 4.dp)
                            .size(35.dp),
                        imageVector = it,
                        contentDescription = ""
                    )
                }
            }
        }

        Spacer(modifier = Modifier.size(3.dp))

        Row(
            Modifier.padding(8.dp)
        ) {
            Text(
                text = subtitle,
                color = MaterialTheme.colorScheme.primary)
        }

        Spacer(modifier = Modifier.size(10.dp))

        Divider(
            color = MaterialTheme.colorScheme.primary,
            thickness = 1.dp
        )
    }
}

