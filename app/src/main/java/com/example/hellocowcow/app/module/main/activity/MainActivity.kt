package com.example.hellocowcow.app.module.main.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.hellocowcow.ui.composables.MainScaffold
import com.example.hellocowcow.ui.theme.XportalConnectTheme
import com.example.hellocowcow.ui.viewmodels.activity.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import timber.log.Timber.Forest.plant

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel by viewModels<MainViewModel>()

    lateinit var address: String
    lateinit var topic: String

    override fun onCreate(savedInstanceState: Bundle?) {
        plant(Timber.DebugTree())

        super.onCreate(savedInstanceState)
        address = intent.getStringExtra("ADDRESS").toString()
        topic = intent.getStringExtra("TOPIC").toString()

        setContent {
            XportalConnectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme
                        .colorScheme
                        .background
                ) { Body() }
            }
        }

        /*val requestParameters = Auth.Params.Request(
            topic = topic,
            chainId = "mvx:D",
            getString(R.string.deep_link_url),
            randomNonce(),
            "https://kotlin.requester.walletconnect-test.com/",
            null,
            null,
            null,
            "Sign in with wallet",
            null,
            null
        )

        AuthClient.request(requestParameters,
            onSuccess = {

            },
            onError = {

            }
        )*/
    }

    //fun randomNonce(): String = Random.nextBytes(16).bytesToHex()

    @Composable
    fun Body() {
        MainScaffold()
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        XportalConnectTheme {
            Body()
        }
    }
}