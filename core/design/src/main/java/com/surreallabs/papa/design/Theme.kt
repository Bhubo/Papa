package com.surreallabs.papa.design

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Voodoo palette: black base, electric purple accents
val VoodooBlack = Color(0xFF0A0A0A)
val VoodooPurple = Color(0xFF8455FF)
val VoodooPurpleDark = Color(0xFF5E35F0)
val VoodooGray = Color(0xFF1A1A1A)

private val DarkColors = darkColorScheme(
    primary = VoodooPurple,
    onPrimary = Color.White,
    secondary = VoodooPurpleDark,
    onSecondary = Color.White,
    background = VoodooBlack,
    onBackground = Color(0xFFEDEDED),
    surface = VoodooGray,
    onSurface = Color(0xFFFAFAFA)
)

@Composable
fun PapaTheme(content: @Composable () -> Unit) {
    val colors = DarkColors // Papa is dark by default
    MaterialTheme(colorScheme = colors, typography = Typography(), content = content)
}
