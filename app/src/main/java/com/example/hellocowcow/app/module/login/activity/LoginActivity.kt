package com.example.hellocowcow.app.module.login.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.example.hellocowcow.R
import com.example.hellocowcow.app.module.BaseActivity
import com.example.hellocowcow.app.module.main.activity.MainActivity
import com.example.hellocowcow.ui.theme.HelloCowCowTheme
import com.example.hellocowcow.ui.viewmodels.activity.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import timber.log.Timber

@AndroidEntryPoint
class LoginActivity : BaseActivity() {

    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.login()
            .subscribeBy (
                onNext = {
                    val intent = Intent(
                        this@LoginActivity,
                        MainActivity::class.java
                    )
                        .putExtra("ADDRESS", viewModel.address)
                        .putExtra("TOPIC", viewModel.topic)
                    startActivity(intent)
                    finish()
                },
                onError = { err ->
                    Timber.tag("LOGIN_ERROR").e(err)
                }
            ).isDisposed
        setContent {
            HelloCowCowTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Body()
                }
            }
        }
    }

    @Composable
    fun Body() {

        val context = LocalContext.current

        Column (
            Modifier.padding(bottom = 50.dp),
            verticalArrangement = Arrangement.Center
        ){
            Text(
                text = "Hello CowCow !",
                fontSize = 22.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            TextButton(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.primary)
                    .border(
                        BorderStroke(0.dp, Color.Red),
                        shape = RoundedCornerShape(0.dp)
                    )
                    .align(Alignment.CenterHorizontally),
                onClick = {

                    Toasty.info(
                        context,
                        "Sent on xPortal"
                    ).show()

                    viewModel.login()
                        .subscribe {
                            val intent = Intent(
                                this@LoginActivity,
                                MainActivity::class.java
                            )
                                .putExtra("ADDRESS", viewModel.address)
                                .putExtra("TOPIC", viewModel.topic)
                            startActivity(intent)
                            finish()
                        }.addTo(disposable)

                    viewModel.connectToWallet { uri ->
                        val xPortalIntent = Intent(
                            Intent.ACTION_VIEW,
                            ("https://xportal.page.link/" +
                                    "?apn=com.multiversx.maiar.wallet" +
                                    "&isi=1519405832&ibi=" +
                                    "com.multiversx.maiar.wallet" +
                                    "&link=https://maiar.com/?wallet-connect=" +
                                    uri)
                                .toUri()
                        )
                        startActivity(xPortalIntent)
                    }
                }
            ) {
                Text(
                    text = "xPortal",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.background,
                    fontFamily = FontFamily(Font(R.font.fredoka_one))
                )
            }
        }
    }

}