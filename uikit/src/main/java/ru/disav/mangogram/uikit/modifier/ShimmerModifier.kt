package ru.disav.mangogram.uikit.modifier

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TileMode
import ru.disav.mangogram.uikit.theme.MangoGramTheme
import ru.disav.mangogram.uikit.theme.CornerRadius12
import ru.disav.mangogram.uikit.theme.Dp4

fun Modifier.shimmerBackground(shape: Shape = RectangleShape): Modifier =
    composed {
        val transition = rememberInfiniteTransition(label = "")

        val translateAnimation by transition.animateFloat(
            initialValue = 0f,
            targetValue = 400f,
            animationSpec = infiniteRepeatable(
                tween(durationMillis = 1500, easing = LinearOutSlowInEasing),
                RepeatMode.Restart
            ),
            label = "",
        )
        val shimmerColors = listOf(
            Color(0xFFCCD0DA).copy(alpha = 0.3f),
            Color(0xFFCCD0DA).copy(alpha = 0.1f),
        )
        val brush = Brush.linearGradient(
            colors = shimmerColors,
            start = Offset(translateAnimation, translateAnimation),
            end = Offset(translateAnimation + 100f, translateAnimation + 100f),
            tileMode = TileMode.Mirror,
        )
        return@composed this.then(background(brush, shape))
    }

fun Modifier.skeletonModifier(): Modifier =
    composed {
        return@composed this.then(
            shadow(
                elevation = Dp4,
                shape = RoundedCornerShape(CornerRadius12),
            )
                .background(
                    shape = RoundedCornerShape(CornerRadius12),
                    color = MangoGramTheme.colorScheme.surfaceBright
                )
                .fillMaxWidth()
                .shimmerBackground(RoundedCornerShape(CornerRadius12))
        )
    }