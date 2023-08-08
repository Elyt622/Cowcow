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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.ContentPasteSearch
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.hellocowcow.domain.models.DomainTransaction

@Composable
fun CustomDialog(
    txTitle: String,
    tx: DomainTransaction,
    setShowDialog: (Boolean) -> Unit
) {
    val context = LocalContext.current

    Dialog(onDismissRequest = { setShowDialog(true) }) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .padding(4.dp)
                .height(70.dp)
                .border(
                    BorderStroke(
                        width = 1.dp,
                        color = Color.Black
                    )
                )
        ) {
            Box(
                contentAlignment = Alignment.TopCenter
            ) {
                Column(modifier = Modifier.padding(4.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = txTitle,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Icon(
                            imageVector = Icons.Filled.Cancel ,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .size(15.dp)
                                .clickable { setShowDialog(false) }
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CircularProgressIndicator(
                            Modifier
                                .padding(end = 2.dp)
                                .size(12.dp),
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = tx.txHash?.substring(0..8)
                                    + "..." +
                                    tx.txHash
                                        ?.substring(
                                            tx.txHash!!.length-8
                                                    until
                                                    tx.txHash!!.length
                                        ),
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(end = 4.dp)
                        )
                        Icon(
                            imageVector = Icons.Filled.ContentPasteSearch,
                            contentDescription = "Go to explorer with txHash",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .size(12.dp)
                                .clickable {
                                    val intent = Intent(Intent.ACTION_VIEW)
                                    val url = Uri.parse("https://explorer.multiversx.com/transactions/${tx.txHash}")
                                    intent.data = url
                                    context.startActivity(intent)
                                }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CustomDialogPreview() {
    CustomDialog(
        "Claim Rewards",
        tx = DomainTransaction(),
        setShowDialog = {}
    )
}