package com.elion.assistant.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val ElionDarkColorScheme = darkColorScheme(
    primary           = Accent,
    onPrimary         = Color.White,
    primaryContainer  = Color(0xFF2D2B6B),
    onPrimaryContainer = TextPrimary,
    secondary         = AccentLight,
    onSecondary       = Color.White,
    background        = Primary,
    onBackground      = TextPrimary,
    surface           = Surface,
    onSurface         = TextPrimary,
    surfaceVariant    = SurfaceVariant,
    onSurfaceVariant  = TextSecondary,
    error             = Danger,
    onError           = Color.White,
    outline           = BorderColor,
)

@Composable
fun ElionTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = ElionDarkColorScheme,
        typography = AppTypography,
        content = content,
    )
}
