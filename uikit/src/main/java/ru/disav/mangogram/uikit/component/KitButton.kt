package ru.disav.mangogram.uikit.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.disav.mangogram.uikit.theme.CornerRadius12
import ru.disav.mangogram.uikit.theme.Dp16
import ru.disav.mangogram.uikit.theme.MangoGramTheme

@Composable
fun KitButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null
) {
    Button(
        onClick = {
            onClick?.invoke()
        },
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        shape = RoundedCornerShape(CornerRadius12),
        colors = ButtonDefaults.buttonColors(
            containerColor = MangoGramTheme.colorScheme.primary,
            disabledContainerColor = MangoGramTheme.colorScheme.surface,
            disabledContentColor = MangoGramTheme.colorScheme.onInverseSurface,
        ),
        contentPadding = PaddingValues(vertical = Dp16, horizontal = Dp16),
        content = {
            Text(
                text = text,
                style = MangoGramTheme.typography.labelLarge,
            )
        }
    )
}

@Preview
@Composable
private fun KitButtonPreview() {
    MangoGramTheme {
        Column {
            KitButton(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Активная кнопка"
            )
            KitButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dp16),
                text = "Неактивная кнопка",
                enabled = false
            )
        }
    }
}
