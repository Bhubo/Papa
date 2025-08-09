package com.surreallabs.papa.equilibrium

// Placeholder math hooks for Spin/Cursor tests
object VerifyMath {
    /** Converts angular velocity (deg/s) to expected pixels moved per second given px/cm + cm/360 */
    fun expectedPxPerSecond(degPerSec: Float, pxPerCm: Float, cmPer360: Float): Float {
        val turnsPerSec = degPerSec / 360f
        val cmPerTurn = cmPer360
        return turnsPerSec * cmPerTurn * pxPerCm
    }
}
