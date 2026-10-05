package com.gorbkostya.passwordapp.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


val LightBackground = Color(0xFFF4F1EC)
val LightCard = Color(0xFFFFFFFF)
val LightMuted = Color(0xFFE9E4DC)
val LightAccent = Color(0xFFD9531E)
val LightOnAccent = Color(0xFFFFFFFF)
val LightTextPrimary = Color(0xFF1C1B19)
val LightTextSecondary = Color(0xFF6F6A62)
val LightBorder = Color(0x141C1B19)



val DarkBackground = Color(0xFF111113)
val DarkCard = Color(0xFF1E1E22)
val DarkMuted = Color(0xFF2E2E34)
val DarkAccent = Color(0xFFFF7A45)
val DarkOnAccent = Color(0xFF1A0D06)
val DarkTextPrimary = Color(0xFFF4F2EE)
val DarkTextSecondary = Color(0xFF9B978F)
val DarkBorder = Color(0x14FFFFFF)



val LightPwDigit = Color(0xFFC2410C)
val LightPwSymbol = Color(0xFF2F5BD8)
val LightStrengthMedium = Color(0xFFD4912A)
val LightStrengthStrong = Color(0xFF2F9E6B)
val LightSwitchOff = Color(0xFFD6D0C6)
val LightSliderThumb = Color(0xFFFFFFFF)
val DarkPwDigit = Color(0xFFFF9A6B)
val DarkPwSymbol = Color(0xFF8AA8FF)
val DarkStrengthMedium = Color(0xFFF2B350)
val DarkStrengthStrong = Color(0xFF4CC38A)
val DarkSwitchOff = Color(0xFF46464F)
val DarkSliderThumb = Color(0xFFF4F2EE)



@Immutable
data class ExtraColors(
    val pwDigit: Color,
    val pwSymbol: Color,
    val strengthMedium: Color,
    val strengthStrong: Color,
    val switchOff: Color,
    val sliderThumb: Color,
)

val LightExtraColors = ExtraColors(
    LightPwDigit,
    LightPwSymbol,
    LightStrengthMedium,
    LightStrengthStrong,
    LightSwitchOff,
    LightSliderThumb
)


val DarkExtraColors = ExtraColors(
    DarkPwDigit,
    DarkPwSymbol,
    DarkStrengthMedium,
    DarkStrengthStrong,
    DarkSwitchOff,
    DarkSliderThumb
)

val LocalExtraColors = staticCompositionLocalOf { LightExtraColors }
