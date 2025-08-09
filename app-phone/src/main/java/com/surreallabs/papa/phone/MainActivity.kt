package com.surreallabs.papa.phone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.surreallabs.papa.design.PapaTheme
import com.surreallabs.papa.equilibrium.EquilibriumSettings
import com.surreallabs.papa.equilibrium.EquilibriumStore
import com.surreallabs.papa.ui.*
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var store: EquilibriumStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        store = EquilibriumStore(this)

        setContent {
            PapaTheme {
                val nav = rememberNavController()
                NavHost(navController = nav, startDestination = "home") {
                    composable("home") {
                        HomeScreen(
                            onBind = { nav.navigate("bind") },
                            onCalibrate = { nav.navigate("calibrate") },
                            onVerify = { nav.navigate("verify") }
                        )
                    }
                    composable("bind") { BindScreen { nav.popBackStack() } }
                    composable("calibrate") {
                        CalibrateHost(store = store) { nav.popBackStack() }
                    }
                    composable("verify") { VerifyScreen { nav.popBackStack() } }
                }
            }
        }
    }
}

@Composable
private fun CalibrateHost(store: EquilibriumStore, onDone: () -> Unit) {
    var current by remember { mutableStateOf<EquilibriumSettings?>(null) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        current = store.current()
    }

    current?.let { init ->
        var working by remember { mutableStateOf(init) }

        CalibrateScreen(
            settings = working,
            onChange = { working = it },
            onSave = {
                scope.launch { store.save(working) }
                onDone()
            }
        )
    } ?: Centered("Loading…")
}
