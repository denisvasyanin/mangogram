package ru.disav.mangogram.uikit.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.disav.mangogram.uikit.theme.MangoGramTheme

@Composable
fun KitRadioButton(
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
) {
    RadioButton(
        selected = selected,
        onClick = onClick,
        modifier = modifier,
        colors = RadioButtonDefaults.colors(
            selectedColor = MangoGramTheme.colorScheme.primary,
            unselectedColor = MangoGramTheme.colorScheme.surface
        )
    )
}

@Preview
@Composable
internal fun KitRadioButtonPreview() {
    MangoGramTheme {
        Surface {
            Column {
                KitRadioButton(
                    selected = true
                )
                KitRadioButton(
                    selected = false
                )
            }
        }
    }
}
