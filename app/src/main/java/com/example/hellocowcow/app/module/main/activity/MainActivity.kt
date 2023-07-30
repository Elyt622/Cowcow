package com.example.hellocowcow.app.module.main.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.hellocowcow.domain.models.DomainAccount
import com.example.hellocowcow.ui.composables.MainScaffold
import com.example.hellocowcow.ui.theme.HelloCowCowTheme
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

        viewModel.getAccount("erd12p7w0mry76538wmpk8cfevpj4062aazavguvxxxlmktsckd9druse3pyay")

        setContent {
            val uiState by viewModel.currentAccount.collectAsState()
            HelloCowCowTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme
                        .colorScheme
                        .background
                ) {
                    when (uiState) {
                        is MainViewModel.UiState.NoData -> { }
                        is MainViewModel.UiState.Success -> {
                            (uiState as MainViewModel.UiState.Success)
                                .data.let { account ->
                                    Body(account)
                                }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun Body(account: DomainAccount) {
        MainScaffold(account)
    }

}