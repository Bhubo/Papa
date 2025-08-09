package com.surreallabs.papa.equilibrium

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "equilibrium")

object Keys {
    val CM_PER_360 = floatPreferencesKey("cm_per_360")
    val PX_PER_CM = floatPreferencesKey("px_per_cm")
    val DEG_PER_SEC = floatPreferencesKey("deg_per_sec")
}

class EquilibriumStore(private val context: Context) {
    val settings: Flow<EquilibriumSettings> = context.dataStore.data.map { p ->
        EquilibriumSettings(
            cmPer360 = p[Keys.CM_PER_360] ?: 30f,
            pxPerCm = p[Keys.PX_PER_CM] ?: 75f,
            degPerSec = p[Keys.DEG_PER_SEC] ?: 720f
        )
    }

    suspend fun current(): EquilibriumSettings = settings.first()

    suspend fun save(s: EquilibriumSettings) {
        context.dataStore.edit { p ->
            p[Keys.CM_PER_360] = s.cmPer360
            p[Keys.PX_PER_CM] = s.pxPerCm
            p[Keys.DEG_PER_SEC] = s.degPerSec
        }
    }
}
