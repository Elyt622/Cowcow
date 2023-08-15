package com.example.hellocowcow.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.hellocowcow.domain.models.DomainAccount
import com.example.hellocowcow.ui.composables.CustomAlert
import com.example.hellocowcow.ui.viewmodels.screen.TestViewModel
import com.walletconnect.sign.client.SignClient
import es.dmoral.toasty.Toasty
import timber.log.Timber

@Composable
fun TestScreen(
    account: DomainAccount,
    topic: String,
    viewModel: TestViewModel
) {

    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    Box(
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                var error = false
                SignClient.request(
                    request = viewModel.buildClaimRewardRequest(account, topic),
                    onError = { err ->
                        error = true
                        Timber.tag("ERROR").e(err.throwable)
                    }
                )
                if (!error) {
                    Toasty.normal(
                        context,
                        "Request sent to xPortal",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        ) {
            Text(
                text = "Claim Rewards",
                color = Color.Black,
                style = MaterialTheme.typography.labelMedium
            )
        }

        when (uiState) {
            is TestViewModel.UiState.Send -> {
                (uiState as TestViewModel.UiState.Send)
                    .tx.let { tx ->
                        CustomAlert(
                            tx = tx,
                            onDismissRequest = { },
                            confirmButton = { }
                        )
                    }
            }

            is TestViewModel.UiState.Error -> {
                (uiState as TestViewModel.UiState.Error)
                    .error.let { err ->
                        Toasty.error(
                            LocalContext.current,
                            err,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }

            else -> {}
        }
    }
}