package ru.disav.mangogram.uikit.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.disav.mangogram.uikit.R
import ru.disav.mangogram.uikit.theme.MangoGramTheme
import ru.disav.mangogram.uikit.theme.CornerRadius12
import ru.disav.mangogram.uikit.theme.Dp64

@Composable
fun KitAlertDialog(
    message: String,
    onDismiss: (() -> Unit)? = null
) {
    AlertDialog(
        onDismissRequest = {
            onDismiss?.invoke()
        },
        title = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = null,
                    modifier = Modifier
                        .size(Dp64)
                        .align(Alignment.Center)
                )
            }
        },
        text = {
            Text(
                text = message,
                modifier = Modifier.fillMaxWidth(),
                style = MangoGramTheme.typography.bodyMedium,
                color = MangoGramTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        },
        shape = RoundedCornerShape(CornerRadius12),
        confirmButton = {},
        dismissButton = {
            KitButton(
                text = stringResource(id = R.string.ok),
                modifier = Modifier.fillMaxWidth(),
                onClick = onDismiss
            )
        },
        tonalElevation = 0.dp
    )
}

@Preview
@Composable
fun KitAlertDialogPreview() {
    MangoGramTheme {
        KitAlertDialog(
            message = "Произошел технический сбой. Повторите попытку входа позже"
        )
    }
}
