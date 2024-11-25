package com.example.hellocowcow.app.module.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.hellocowcow.app.module.BaseActivity
import com.example.hellocowcow.app.module.main.activity.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    startActivity(
      Intent(
        this@SplashActivity,
        MainActivity::class.java
      )
    )
    installSplashScreen()
    finish()
  }
}