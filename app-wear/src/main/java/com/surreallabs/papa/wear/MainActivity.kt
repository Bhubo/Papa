package com.surreallabs.papa.wear

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.surreallabs.papa.design.PapaTheme
import com.surreallabs.papa.ui.PrimaryButton
import com.surreallabs.papa.ui.PapaHeader

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { PapaTheme { WearHome() } }
    }
}

@Composable
fun WearHome() {
    Column(Modifier.fillMaxSize().padding(12.dp)) {
        PapaHeader("Papa", "Wear OS")
        PrimaryButton("Bind", onClick = { /* TODO */ }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        PrimaryButton("Calibrate", onClick = { /* TODO */ }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        PrimaryButton("Verify", onClick = { /* TODO */ }, modifier = Modifier.fillMaxWidth())
    }
}
