package ru.disav.mangogram.uikit.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import ru.disav.mangogram.uikit.KitIcons
import ru.disav.mangogram.uikit.theme.CornerRadius12
import ru.disav.mangogram.uikit.theme.Dp8
import ru.disav.mangogram.uikit.theme.MangoGramTheme

@Composable
fun KitActionBarButton(
    text: String,
    onClick: (() -> Unit)? = null
) {
    Button(
        modifier = Modifier
            .padding(end = Dp8),
        onClick = { onClick?.invoke() },
        shape = RoundedCornerShape(CornerRadius12),
        colors = ButtonDefaults.buttonColors(
            containerColor = MangoGramTheme.colorScheme.surfaceBright,
        ),
        contentPadding = PaddingValues(Dp8),
    ) {

        Text(
            text = text,
            modifier = Modifier.padding(end = Dp8),
            style = MangoGramTheme.typography.labelLarge,
            color = MangoGramTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        Icon(
            painter = painterResource(id = KitIcons.ArrowForward),
            contentDescription = null,
            tint = MangoGramTheme.colorScheme.primary
        )
    }
}

@Preview
@Composable
fun KitActionBarButtonPreview() {
    MangoGramTheme {
        Column {
            KitActionBarButton(
                text = "Редактировать"
            )
        }
    }
}
