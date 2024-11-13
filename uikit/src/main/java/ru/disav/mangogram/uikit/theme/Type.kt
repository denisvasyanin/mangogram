package ru.disav.mangogram.uikit.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.disav.mangogram.uikit.R

val mulishFontFamily = FontFamily(
    Font(R.font.mulish_light, FontWeight.Light),
    Font(R.font.mulish_normal, FontWeight.Normal),
    Font(R.font.mulish_semibold, FontWeight.SemiBold),
    Font(R.font.mulish_bold, FontWeight.Bold),
    Font(R.font.mulish_extra_bold, FontWeight.ExtraBold)
)

data class BefitTypography(
    val h1: TextStyle = TextStyle(
        fontFamily = mulishFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),
    val h2: TextStyle = TextStyle(
        fontFamily = mulishFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ),
    val h3: TextStyle = TextStyle(
        fontFamily = mulishFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 18.sp
    ),
    val h4: TextStyle = TextStyle(
        fontFamily = mulishFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),

    val bodyExtraLarge: TextStyle = TextStyle(
        fontFamily = mulishFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp
    ),

    val bodyLarge: TextStyle = TextStyle(
        fontFamily = mulishFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
    ),

    val bodyMedium: TextStyle = TextStyle(
        fontFamily = mulishFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),

    val bodySmall: TextStyle = TextStyle(
        fontFamily = mulishFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp
    ),

    val labelLarge: TextStyle = TextStyle(
        fontFamily = mulishFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 16.sp
    ),
    val labelMedium: TextStyle = TextStyle(
        fontFamily = mulishFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 14.sp
    ),
    val labelSmall: TextStyle = TextStyle(
        fontFamily = mulishFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 12.sp
    )
)
