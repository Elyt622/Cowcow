package com.example.hellocowcow.app.module.main.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hellocowcow.domain.models.DomainAccount
import com.example.hellocowcow.ui.composables.MainScaffold
import com.example.hellocowcow.ui.theme.HelloCowCowTheme
import com.example.hellocowcow.ui.viewmodels.activity.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty
import timber.log.Timber
import timber.log.Timber.Forest.plant

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    private lateinit var address: String
    private lateinit var topic: String

    override fun onCreate(savedInstanceState: Bundle?) {
        plant(Timber.DebugTree())

        super.onCreate(savedInstanceState)
        address = intent.getStringExtra("ADDRESS").toString()
        topic = intent.getStringExtra("TOPIC").toString()

        viewModel.getAccount(address)

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
                        is MainViewModel.UiState.Loading -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.width(60.dp),
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                        is MainViewModel.UiState.Success -> {
                            (uiState as MainViewModel.UiState.Success)
                                .data.let { account ->
                                    Body(account, topic)
                                }
                        }
                        is MainViewModel.UiState.Error -> {
                            (uiState as MainViewModel.UiState.Error)
                                .error.let { err ->
                                    Toasty.error(
                                        baseContext,
                                        err,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun Body(
        account: DomainAccount,
        topic: String
    ) {
        MainScaffold(account, topic)
    }

}