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
import timber.log.Timber

@AndroidEntryPoint
class LoginActivity : BaseActivity() {

  private val viewModel by viewModels<LoginViewModel>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      HelloCowCowTheme(dynamicColor = false) {
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

    Column(
      Modifier.padding(bottom = 50.dp),
      verticalArrangement = Arrangement.Center
    ) {
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
          login()
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

  private fun login() =
    viewModel.handleExistingSession(
      onSessionAvailable = { address, topic ->
        Timber.tag("Session").d("Reusing existing session with topic: $topic")
        Toasty.info(this, "Session réutilisée : $address").show()
        navigateToMainActivity(address, topic)
      },
      onNoSession = {
        Timber.tag("Session").d("No existing session found, starting a new connection.")
        startNewConnection()
      }
    )

  private fun navigateToMainActivity(address: String, topic: String) {
    val intent = Intent(this, MainActivity::class.java).apply {
      putExtra("ADDRESS", address)
      putExtra("TOPIC", topic)
    }
    startActivity(intent)
    finish()
  }

  private fun startNewConnection() {
    viewModel.connectToWallet(
      onProposedSequence = { uri ->
        val xPortalIntent = Intent(
          Intent.ACTION_VIEW,
          ("https://maiar.page.link/" +
              "?apn=com.multiversx.maiar.wallet" +
              "&isi=1519405832&ibi=" +
              "com.multiversx.maiar.wallet" +
              "&link=https://maiar.com/?wallet-connect=" +
              uri)
            .toUri()
        )
        startActivity(xPortalIntent)
      }
    )
  }

}