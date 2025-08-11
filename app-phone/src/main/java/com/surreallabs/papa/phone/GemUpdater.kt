package com.surreallabs.papa.phone

import android.content.Context
import android.util.Log
import com.google.android.gms.wearable.PutDataMapRequest
import com.google.android.gms.wearable.Wearable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

object GemUpdater {

    private const val TAG = "GemUpdater"
    private const val GEM_PATH = "/current_gem_key" // Must match path in GemComplicationService
    private const val GEM_KEY_FIELD = "gem_key"     // Must match field in GemComplicationService

    fun updateWatchComplication(context: Context, gemKey: String) {
        if (gemKey.isBlank()) {
            Log.w(TAG, "Gem key is blank, not updating.")
            return
        }

        Log.d(TAG, "Attempting to update watch complication with gemKey: $gemKey")

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val putDataMapReq = PutDataMapRequest.create(GEM_PATH).apply {
                    dataMap.putString(GEM_KEY_FIELD, gemKey)
                    setUrgent() // Make it urgent for faster sync
                }
                val putDataReq = putDataMapReq.asPutDataRequest()

                Wearable.getDataClient(context.applicationContext).putDataItem(putDataReq).await()
                Log.i(TAG, "Successfully sent gemKey '$gemKey' to wearable.")

            } catch (e: Exception) {
                Log.e(TAG, "Failed to send gemKey '$gemKey' to wearable", e)
            }
        }
    }
}
