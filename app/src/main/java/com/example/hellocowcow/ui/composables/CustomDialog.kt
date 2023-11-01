package com.example.hellocowcow.ui.composables

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.hellocowcow.domain.models.DomainTransaction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAlert(
    tx: DomainTransaction
) {
    val context = LocalContext.current
    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            }
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .height(300.dp)
                    .border(
                        BorderStroke(
                            width = 1.dp,
                            color = Color.Black
                        )
                    ),
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.large,
                tonalElevation = AlertDialogDefaults.TonalElevation
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Transaction Details",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.background
                            )
                        }

                        Icon(
                            imageVector = Icons.Filled.Cancel,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.background,
                            modifier = Modifier
                                .size(24.dp)
                                .clickable { openDialog.value = false }
                        )
                    }

                    Text(
                        text = "Transaction ${tx.status}",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.background
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Status: ${tx.status}",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.background
                        )
                        Icon(
                            modifier = Modifier.size(12.5.dp),
                            imageVector = Icons.Filled.HourglassTop,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.background
                        )
                    }

                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.background,
                            contentColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Row(
                            modifier = Modifier.padding(8.dp),
                        ) {
                            Text(
                                text = "Tx Hash: ",
                                style = MaterialTheme.typography.labelMedium
                            )
                            Text(
                                text = tx.txHash?.substring(0..8)
                                        + "..." +
                                        tx.txHash
                                            ?.substring(
                                                tx.txHash!!.length - 8
                                                        until
                                                        tx.txHash!!.length
                                            ),
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }

                    TextButton(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.background,
                        ),
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW)
                            val url =
                                Uri.parse("https://explorer.multiversx.com/transactions/${tx.txHash}")
                            intent.data = url
                            context.startActivity(intent)
                            openDialog.value = false
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = "VIEW ON EXPLORER",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}
