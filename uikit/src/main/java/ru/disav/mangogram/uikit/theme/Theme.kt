package ru.disav.mangogram.uikit.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

@Composable
fun MangoGramTheme(
    colorScheme: MangogramColors = MangoGramTheme.colorScheme,
    typography: BefitTypography = MangoGramTheme.typography,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColorScheme provides colorScheme,
        LocalTypography provides typography,
        content = content
    )
}

object MangoGramTheme {

    val colorScheme: MangogramColors
        @Composable
        get() = LocalColorScheme.current

    val typography: BefitTypography
        @Composable
        get() = LocalTypography.current

}

internal val LocalColorScheme = staticCompositionLocalOf { lightColors() }

internal val LocalTypography = staticCompositionLocalOf { BefitTypography() }
