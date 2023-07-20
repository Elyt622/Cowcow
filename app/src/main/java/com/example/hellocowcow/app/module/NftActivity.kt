package com.example.hellocowcow.app.module

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.hellocowcow.ui.composables.MainScaffold
import com.example.hellocowcow.ui.theme.XportalConnectTheme
import com.example.hellocowcow.ui.viewmodels.screen.NftViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import timber.log.Timber.Forest.plant

@AndroidEntryPoint
class NftActivity : ComponentActivity() {

    val viewModel by viewModels<NftViewModel>()

    lateinit var identifier: String

    override fun onCreate(savedInstanceState: Bundle?) {
        plant(Timber.DebugTree())

        super.onCreate(savedInstanceState)
        identifier = intent.getStringExtra("IDENTIFIER").toString()

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
    }

    @Composable
    fun Body() {
        MainScaffold()
    }

}