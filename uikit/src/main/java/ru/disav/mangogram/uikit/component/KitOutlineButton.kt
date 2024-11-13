package ru.disav.mangogram.uikit.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.disav.mangogram.uikit.theme.MangoGramTheme
import ru.disav.mangogram.uikit.theme.CornerRadius12
import ru.disav.mangogram.uikit.theme.Dp1
import ru.disav.mangogram.uikit.theme.Dp16

@Composable
fun KitOutlinedButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null
) {
    OutlinedButton(
        onClick = {
            onClick?.invoke()
        },
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(CornerRadius12),
        colors = ButtonDefaults.buttonColors(
            containerColor = MangoGramTheme.colorScheme.surfaceBright,
        ),
        contentPadding = PaddingValues(vertical = Dp16),
        border = BorderStroke(Dp1, MangoGramTheme.colorScheme.outline),
        content = {
            Text(
                text = text,
                style = MangoGramTheme.typography.labelLarge,
                color = MangoGramTheme.colorScheme.onSurface
            )
        }
    )
}

@Preview
@Composable
internal fun KitOutlinedButtonPreview() {
    MangoGramTheme {
        Column {
            KitOutlinedButton(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Активная кнопка"
            )
        }
    }
}
