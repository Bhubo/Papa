package com.surreallabs.papa.tv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.surreallabs.papa.design.PapaTheme
import com.surreallabs.papa.ui.PapaHeader
import com.surreallabs.papa.ui.PrimaryButton

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { PapaTheme { TvHome() } }
    }
}

@Composable
fun TvHome() {
    Column(Modifier.fillMaxSize().padding(40.dp)) {
        PapaHeader("Papa", "Android TV")
        PrimaryButton("Bind", onClick = { /* TODO */ }, modifier = Modifier)
        Spacer(Modifier.height(16.dp))
        PrimaryButton("Calibrate", onClick = { /* TODO */ })
        Spacer(Modifier.height(16.dp))
        PrimaryButton("Verify", onClick = { /* TODO */ })
    }
}
