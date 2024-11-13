package ru.disav.mangogram.uikit.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.disav.mangogram.uikit.theme.MangoGramTheme

@Composable
fun KitCheckbox(
    checked: Boolean,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onCheckedChange: ((Boolean) -> Unit)? = null,
) {
    Checkbox(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        enabled = enabled,
        colors = CheckboxDefaults.colors(
            checkedColor = MangoGramTheme.colorScheme.primary,
            uncheckedColor = MangoGramTheme.colorScheme.surface
        )
    )
}

@Preview
@Composable
fun KitCheckboxPreview() {
    MangoGramTheme {
        Surface {
            Column {
                KitCheckbox(
                    checked = true
                )
                KitCheckbox(
                    checked = false
                )
            }
        }
    }
}
