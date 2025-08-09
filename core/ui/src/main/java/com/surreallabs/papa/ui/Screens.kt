package com.surreallabs.papa.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.surreallabs.papa.equilibrium.EquilibriumSettings

@Composable
fun HomeScreen(onBind: () -> Unit, onCalibrate: () -> Unit, onVerify: () -> Unit) {
    Scaffold(topBar = { TopAppBar(title = { Text("Papa") }) }) { pad ->
        Column(Modifier.padding(pad).padding(20.dp)) {
            PapaHeader("Equilibrium", "Local‑first. Bound. Private.")
            PrimaryButton("Bind a device", onBind, Modifier.fillMaxWidth())
            Spacer(Modifier.height(12.dp))
            PrimaryButton("Calibrate feel", onCalibrate, Modifier.fillMaxWidth())
            Spacer(Modifier.height(12.dp))
            PrimaryButton("Verify (Spin/Cursor)", onVerify, Modifier.fillMaxWidth())
        }
    }
}

@Composable
fun BindScreen(onDone: () -> Unit) {
    // Stub: Later swap for BLE/NFC/QR bind flow
    Centered("Bind‑only exchange ready.", actionText = "Mark as Bound", onAction = onDone)
}

@Composable
fun CalibrateScreen(settings: EquilibriumSettings, onChange: (EquilibriumSettings) -> Unit, onSave: () -> Unit) {
    var cmPer360 by remember { mutableStateOf(settings.cmPer360) }
    var pxPerCm by remember { mutableStateOf(settings.pxPerCm) }
    var degPerSec by remember { mutableStateOf(settings.degPerSec) }

    Scaffold(topBar = { TopAppBar(title = { Text("Calibrate Feel") }) }) { pad ->
        Column(Modifier.padding(pad).padding(20.dp)) {
            Text("cm / 360°: ${'$'}{cmPer360}")
            Slider(value = cmPer360, onValueChange = { cmPer360 = it }, valueRange = 1f..100f)

            Text("px / cm: ${'$'}{pxPerCm}")
            Slider(value = pxPerCm, onValueChange = { pxPerCm = it }, valueRange = 10f..1000f)

            Text("deg / s: ${'$'}{degPerSec}")
            Slider(value = degPerSec, onValueChange = { degPerSec = it }, valueRange = 10f..2000f)

            Spacer(Modifier.height(12.dp))
            PrimaryButton("Save", onClick = {
                onChange(EquilibriumSettings(cmPer360, pxPerCm, degPerSec))
                onSave()
            }, modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
fun VerifyScreen(onDone: () -> Unit) {
    // Stub: will render spin speed / cursor pixel delta live in next pass
    Centered("Run Spin & Cursor tests.", actionText = "Equilibrium achieved", onAction = onDone)
}
