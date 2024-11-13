package ru.disav.mangogram.uikit.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.disav.mangogram.uikit.KitIcons
import ru.disav.mangogram.uikit.R
import ru.disav.mangogram.uikit.theme.MangoGramTheme
import ru.disav.mangogram.uikit.theme.Dp16

@Composable
fun KitConfirmDialog(
    title: String,
    message: String,
    onConfirm: (() -> Unit)? = null,
    onDismiss: (() -> Unit)? = null
) {
    AlertDialog(
        icon = {
            Image(
                painter = painterResource(id = KitIcons.Info),
                null,
                colorFilter = ColorFilter.tint(
                    Color.Black
                )
            )
        },
        containerColor = MangoGramTheme.colorScheme.surfaceBright,
        onDismissRequest = {
            onDismiss?.invoke()
        },
        title = {
            Text(
                text = title,
                style = MangoGramTheme.typography.h2
            )
        },
        text = {
            Text(
                text = message, style = MangoGramTheme.typography.bodyMedium
            )
        },
        confirmButton = {},
        dismissButton = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                KitButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onDismiss,
                    text = stringResource(id = R.string.buttonNo)
                )

                Spacer(modifier = Modifier.height(Dp16))

                KitButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onConfirm,
                    text = stringResource(id = R.string.buttonYes)
                )
            }
        }
    )
}

@Composable
@Preview
fun ExitAlertPreview() {
    KitConfirmDialog(
        title = "Title",
        message = "Are you sure?",
    )
}