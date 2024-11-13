package ru.disav.mangogram.uikit.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color


internal val White1 = Color(0xFFFFFFFF)
internal val White3 = Color(0xFFF1F3F8)
internal val White4 = Color(0xFFE8EBF1)
internal val LightGray2 = Color(0xFFCCD0DA)
internal val DarkGray3 = Color(0xFF6D707B)
internal val Black4 = Color(0xFF07090F)
internal val MangoSuperLight = Color(0xFFffdcb4)
internal val MangoLight = Color(0xADFFDCB4)
internal val Mango = Color(0xFFffa466)

@Immutable
data class MangogramColors(
    val primary: Color,
    val onPrimary: Color,
    val primaryLight: Color,
    val onPrimaryLight: Color,
    val surfaceBright: Color,
    val surface: Color,
    val surfaceDim: Color,
    val surfaceDimVariant: Color,
    val onSurface: Color,
    val onSurfaceVariant: Color,
    val inverseSurface: Color,
    val onInverseSurface: Color,
    val outline: Color,
    val outlineVariant: Color,
)

fun lightColors(
    primary: Color = Mango,
    onPrimary: Color = White1,
    primaryLight: Color = MangoLight,
    onPrimaryLight: Color = White1,
    surfaceBright: Color = White1,
    surface: Color = MangoSuperLight,
    surfaceDim: Color = White3,
    surfaceDimVariant: Color = White4,
    onSurface: Color = Black4,
    onSurfaceVariant: Color = DarkGray3,
    inverseSurface: Color = Black4,
    onInverseSurface: Color = White1,
    outline: Color = White4,
    outlineVariant: Color = MangoLight
) = MangogramColors(
    primary = primary,
    onPrimary = onPrimary,
    primaryLight = primaryLight,
    onPrimaryLight = onPrimaryLight,
    surfaceBright = surfaceBright,
    surface = surface,
    surfaceDim = surfaceDim,
    surfaceDimVariant = surfaceDimVariant,
    onSurface = onSurface,
    onSurfaceVariant = onSurfaceVariant,
    inverseSurface = inverseSurface,
    onInverseSurface = onInverseSurface,
    outline = outline,
    outlineVariant = outlineVariant
)
