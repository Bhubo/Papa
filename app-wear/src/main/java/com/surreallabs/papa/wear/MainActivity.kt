package com.surreallabs.papa.wear

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.rememberScalingLazyListState
import com.surreallabs.papa.design.PapaTheme
import com.surreallabs.papa.ui.PrimaryButton
import com.surreallabs.papa.ui.PapaHeader

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { PapaTheme { WearHome() } }
    } // Closing brace for onCreate was added here
}

@Composable
fun WearHome() {
    val listState = rememberScalingLazyListState()
    ScalingLazyColumn(
        modifier = Modifier.fillMaxSize().padding(12.dp),
        state = listState,
        horizontalAlignment = Alignment.CenterHorizontally // Center content horizontally
    ) {
        item { PapaHeader("Papa", "Wear OS") }
        item { Spacer(Modifier.height(4.dp)) } // Reduced spacer a bit
        item { PrimaryButton("Bind", onClick = { /* TODO */ }, modifier = Modifier.fillMaxWidth()) }
        item { Spacer(Modifier.height(8.dp)) }
        item { PrimaryButton("Calibrate", onClick = { /* TODO */ }, modifier = Modifier.fillMaxWidth()) }
        item { Spacer(Modifier.height(8.dp)) }
        item { PrimaryButton("Verify", onClick = { /* TODO */ }, modifier = Modifier.fillMaxWidth()) }
    }
}
