package ru.disav.mangogram.app

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import ru.disav.mangogram.R
import ru.disav.mangogram.uikit.theme.Dp256
import ru.disav.mangogram.uikit.theme.Dp64
import ru.disav.mangogram.uikit.theme.MangoGramTheme

@Composable
fun InitialScene(
) = Box(
    modifier = Modifier
        .fillMaxSize()
        .background(MangoGramTheme.colorScheme.surfaceBright),
    contentAlignment = Alignment.Center
) {
    var animate by remember { mutableStateOf(false) }

    val heightInDp = animateDpAsState(
        targetValue = if (animate) Dp256 else Dp64,
        animationSpec = tween(
            durationMillis = 1000,
        ),
        label = ""
    )

    LaunchedEffect(Unit) {
        animate = true
    }

    Image(
        painter = painterResource(R.drawable.mango_108),
        contentDescription = null,
        modifier = Modifier.size(heightInDp.value)
    )
}


@Preview
@Composable
private fun InitialScenePreview() = MangoGramTheme {
    InitialScene()
}
