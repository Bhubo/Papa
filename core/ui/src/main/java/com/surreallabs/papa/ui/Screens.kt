package com.surreallabs.papa.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.surreallabs.papa.equilibrium.EquilibriumSettings // Assuming R class is available (e.g., com.surreallabs.papa.core.ui.R or similar)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onBind: () -> Unit, onCalibrate: () -> Unit, onVerify: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Voodoo") },
                navigationIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.loa_baron),
                        contentDescription = "Voodoo Doll",
                        modifier = Modifier.size(40.dp)
                    )
                }
            )
        }
    ) { pad ->
        Column(
            Modifier
                .padding(pad)
                .padding(20.dp)
        ) {
            // Inlined PapaHeader
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Voodoo", style = MaterialTheme.typography.headlineLarge)
                Text(text = "Personal Aware Private Architecture", style = MaterialTheme.typography.titleSmall)
            }

            // Bind a device
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.purple_gem),
                    contentDescription = "Bind Device Gem",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { onBind() }
                        .padding(end = 8.dp)
                )
                // Inlined PrimaryButton
                Button(onClick = onBind, modifier = Modifier.weight(1f)) {
                    Text("Bind a device")
                }
            }
            Spacer(Modifier.height(12.dp))

            // Calibrate feel
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.green_gem),
                    contentDescription = "Calibrate Feel Gem",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { onCalibrate() }
                        .padding(end = 8.dp)
                )
                // Inlined PrimaryButton
                Button(onClick = onCalibrate, modifier = Modifier.weight(1f)) {
                    Text("Calibrate feel")
                }
            }
            Spacer(Modifier.height(12.dp))

            // Verify (Spin/Cursor)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.blue_gem),
                    contentDescription = "Verify Gem",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { onVerify() }
                        .padding(end = 8.dp)
                )
                // Inlined PrimaryButton
                Button(onClick = onVerify, modifier = Modifier.weight(1f)) {
                    Text("Verify (Spin/Cursor)")
                }
            }
        }
    }
}

@Composable
fun BindScreen(onDone: () -> Unit) {
    // Inlined Centered
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.loa_poppet),
            contentDescription = "Binding Poppet",
            modifier = Modifier.size(128.dp)
        )
        Spacer(Modifier.height(20.dp))
        // Inlined PrimaryButton (as Button)
        Button(onClick = onDone, modifier = Modifier.fillMaxWidth(0.75f)) {
            Text("Bind")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalibrateScreen(settings: EquilibriumSettings, onChange: (EquilibriumSettings) -> Unit, onSave: () -> Unit) {
    var cmPer360 by remember { mutableStateOf(settings.cmPer360) }
    var pxPerCm by remember { mutableStateOf(settings.pxPerCm) }
    var degPerSec by remember { mutableStateOf(settings.degPerSec) }

    Scaffold(topBar = { TopAppBar(title = { Text("Calibrate Feel") }) }) { pad ->
        Column(
            Modifier
                .padding(pad)
                .padding(20.dp)
        ) {
            Text("cm / 360°: ${"%.2f".format(cmPer360)}")
            Slider(value = cmPer360, onValueChange = { cmPer360 = it }, valueRange = 1f..100f)

            Text("px / cm: ${"%.0f".format(pxPerCm)}")
            Slider(value = pxPerCm, onValueChange = { pxPerCm = it }, valueRange = 10f..1000f)

            Text("deg / s: ${"%.0f".format(degPerSec)}")
            Slider(value = degPerSec, onValueChange = { degPerSec = it }, valueRange = 10f..2000f)

            Spacer(Modifier.height(12.dp))
            // Inlined PrimaryButton
            Button(
                onClick = {
                    onChange(EquilibriumSettings(cmPer360, pxPerCm, degPerSec))
                    onSave()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save")
            }
        }
    }
}

@Composable
fun VerifyScreen(onDone: () -> Unit) {
    // Inlined Centered
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Run Spin & Cursor tests.", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(20.dp))
        // Inlined PrimaryButton (as Button)
        Button(onClick = onDone) {
            Text("Equilibrium achieved")
        }
    }
}

/*
@Composable
fun PapaHeader(title: String, subtitle: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = title, style = MaterialTheme.typography.headlineLarge)
        Text(text = subtitle, style = MaterialTheme.typography.titleSmall)
    }
}

@Composable
fun PrimaryButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(onClick = onClick, modifier = modifier) {
        Text(text)
    }
}

@Composable
fun Centered(text: String, actionText: String? = null, onAction: (() -> Unit)? = null) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text, style = MaterialTheme.typography.headlineMedium)
        if (actionText != null && onAction != null) {
            Spacer(Modifier.height(20.dp))
            Button(onClick = onAction) {
                Text(actionText)
            }
        }
    }
}
*/
