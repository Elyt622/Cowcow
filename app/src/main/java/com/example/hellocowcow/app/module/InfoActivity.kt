package com.example.hellocowcow.app.module

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.hellocowcow.ui.theme.HelloCowCowTheme

class InfoActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.getStringExtra("IMAGE")
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


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MyScaffold(
        title: String,
        upAvailable: Boolean,
        onUpClicked: () -> Unit,
        content: @Composable (PaddingValues) -> Unit
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    },
                    colors = TopAppBarDefaults
                        .topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.background
                        ),
                    navigationIcon = {
                        if (upAvailable) {
                            IconButton(onClick = { onUpClicked() }) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Back",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                )
            },
            containerColor = Color.Transparent,
            content = content
        )
    }

    @Composable
    fun Body() {
        val context = LocalContext.current as Activity
        MyScaffold(
            title = "Test",
            upAvailable = true,
            onUpClicked = { context.finish() },
            content = { }
        )
    }

    @Composable
    fun TheCity() {

    }

    @Composable
    fun CowCow() {

    }
}
