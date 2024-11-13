package ru.disav.mangogram.uikit.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import ru.disav.mangogram.uikit.theme.MangoGramTheme
import ru.disav.mangogram.uikit.theme.Dp32

@Composable
fun KitClickableText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MangoGramTheme.colorScheme.primary,
    style: TextStyle = MangoGramTheme.typography.bodySmall,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
                onClick = {
                    onClick?.invoke()
                }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            modifier = Modifier,
            style = style,
            color = color
        )
    }
}

@Preview
@Composable
fun KitClickableTextPreview() {
    MangoGramTheme {
        Box(
            modifier = Modifier
                .background(MangoGramTheme.colorScheme.surfaceBright)
                .padding(Dp32)
        ) {
            KitClickableText(
                modifier = Modifier,
                text = "Перейти"
            )
        }
    }
}
