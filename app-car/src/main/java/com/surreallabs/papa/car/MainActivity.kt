package com.surreallabs.papa.car

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
        setContent { PapaTheme { CarHome() } }
    }
}

@Composable
fun CarHome() {
    Column(Modifier.fillMaxSize().padding(24.dp)) {
        PapaHeader("Papa", "Automotive OS")
        PrimaryButton("Quick Verify", onClick = { /* TODO */ })
        Spacer(Modifier.height(12.dp))
        PrimaryButton("Calibrate", onClick = { /* TODO */ })
    }
}
