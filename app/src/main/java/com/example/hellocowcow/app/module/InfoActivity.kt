package com.example.hellocowcow.app.module

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.hellocowcow.R
import com.example.hellocowcow.ui.theme.HelloCowCowTheme

class InfoActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val imageName = intent.getStringExtra("IMAGE").toString()
        setContent {
            HelloCowCowTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Body(imageName)
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
    fun Body(imageName: String) {
        val context = LocalContext.current as Activity

        MyScaffold(
            title = "Test",
            upAvailable = true,
            onUpClicked = { context.finish() },
            content = {
                when(imageName) {
                    "COWCOW" -> CowCow()
                    "THE_CITY" -> TheCity()
                }
            }
        )
    }

    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    fun TheCity() {
        Column(
            modifier = Modifier.padding(top = 60.dp)
        ) {
            GlideImage(
                model = "",
                contentDescription = ""
            ) {
                it.load(R.drawable.bg_remake_new)
            }
            Column(
                Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "BUILDING THE CITY", style = MaterialTheme.typography.bodyLarge)
                Text(text = "Let's moove together", style = MaterialTheme.typography.labelMedium)
                Text(text = "Cow Cow is not just an NFT project, it is a moovement.  \n" +
                    "For all the ones looking for a home, we are building you a city.  \n" +
                    "Are you ready to move into The City?\n", style = MaterialTheme.typography.labelMedium)
            }
        }
    }

    @Composable
    fun CowCow() {
        Text(
            text = "COWCOW",
            color = Color.Black,
            modifier = Modifier.padding(top = 60.dp)
        )
    }
}
