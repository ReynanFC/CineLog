package com.reynanfc.cinelog.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val CineLogColorScheme = darkColorScheme(
    primary = CineLogCoral,
    onPrimary = CineLogBackground,
    secondary = CineLogCoral,
    background = CineLogBackground,
    onBackground = Color.White,
    surface = CineLogSurface,
    onSurface = Color.White,
    surfaceVariant = CineLogSurfaceVariant,
    onSurfaceVariant = CineLogOnSurfaceVariant
)

@Composable
fun CineLogTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = CineLogColorScheme,
        typography = Typography,
        content = content
    )
}
