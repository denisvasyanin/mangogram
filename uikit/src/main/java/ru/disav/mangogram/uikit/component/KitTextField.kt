package ru.disav.mangogram.uikit.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import ru.disav.mangogram.uikit.theme.CornerRadius12
import ru.disav.mangogram.uikit.theme.Dp16
import ru.disav.mangogram.uikit.theme.MangoGramTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KitTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeholderText: String = "",
    onValueChange: ((String) -> Unit)? = null,
    enabled: Boolean = true,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange?.invoke(it) },
        modifier = modifier,
        textStyle = MangoGramTheme.typography.bodyMedium,
        placeholder = {
            Text(
                text = placeholderText,
                style = MangoGramTheme.typography.bodyMedium,
                color = MangoGramTheme.colorScheme.onSurfaceVariant,
            )
        },
        trailingIcon = trailingIcon,
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedTextColor = MangoGramTheme.colorScheme.onSurface,
            disabledTextColor = MangoGramTheme.colorScheme.onSurfaceVariant,
            containerColor = if (enabled) MangoGramTheme.colorScheme.primaryLight else Color.Transparent,
            focusedBorderColor = MangoGramTheme.colorScheme.primary,
            unfocusedBorderColor = MangoGramTheme.colorScheme.outlineVariant,
            disabledBorderColor = MangoGramTheme.colorScheme.outlineVariant
        ),
        shape = RoundedCornerShape(CornerRadius12),
        enabled = enabled,
        isError = isError,
        visualTransformation = visualTransformation
    )
}

@Preview
@Composable
fun KitTextFieldPreview() {
    MangoGramTheme {
        Column(
            modifier = Modifier
                .background(
                    color = MangoGramTheme.colorScheme.surfaceBright
                )
                .padding(Dp16)
        ) {
            KitTextField(
                value = "Enabled state",
                enabled = true
            )

            Spacer(modifier = Modifier.height(Dp16))

            KitTextField(
                value = "Disabled state",
                enabled = false
            )

            Spacer(modifier = Modifier.height(Dp16))

            KitTextField(
                value = "",
                placeholderText = "Placeholder Text",
                enabled = false
            )
        }
    }
}
