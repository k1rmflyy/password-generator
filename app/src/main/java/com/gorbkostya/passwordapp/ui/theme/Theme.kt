package com.gorbkostya.passwordapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    background = DarkBackground,
    onBackground = DarkTextPrimary,
    surface = DarkCard,
    onSurface = DarkTextPrimary,
    onSurfaceVariant = DarkTextSecondary,
    surfaceVariant = DarkMuted,
    primary = DarkAccent,
    onPrimary = DarkOnAccent,
    outlineVariant = DarkBorder
)

private val LightColorScheme = lightColorScheme(
    background = LightBackground,
    onBackground = LightTextPrimary,
    surface = LightCard,
    onSurface = LightTextPrimary,
    onSurfaceVariant = LightTextSecondary,
    surfaceVariant = LightMuted,
    primary = LightAccent,
    onPrimary = LightOnAccent,
    outlineVariant = LightBorder


)

@Composable
fun PasswordAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val extraColors = if (darkTheme) DarkExtraColors else LightExtraColors
    CompositionLocalProvider(LocalExtraColors provides extraColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}