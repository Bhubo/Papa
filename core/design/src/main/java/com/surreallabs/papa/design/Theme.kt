package com.surreallabs.papa.design

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Vodou palette: black base, electric purple accents
val VodouBlack = Color(0xFF0A0A0A)
val VodouPurple = Color(0xFF8455FF)
val VodouPurpleDark = Color(0xFF5E35F0)
val VodouGray = Color(0xFF1A1A1A)

private val DarkColors = darkColorScheme(
    primary = VodouPurple,
    onPrimary = Color.White,
    secondary = VodouPurpleDark,
    onSecondary = Color.White,
    background = VodouBlack,
    onBackground = Color(0xFFEDEDED),
    surface = VodouGray,
    onSurface = Color(0xFFFAFAFA)
)

@Composable
fun PapaTheme(content: @Composable () -> Unit) {
    val colors = DarkColors // Papa is dark by default
    MaterialTheme(colorScheme = colors, typography = Typography(), content = content)
}
