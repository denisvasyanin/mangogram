package ru.disav.mangogram.features.chat.presentation.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.disav.mangogram.uikit.theme.CornerRadius12
import ru.disav.mangogram.uikit.theme.Dp16
import ru.disav.mangogram.uikit.theme.MangoGramTheme

@Composable
fun MessageItem(
    text: String,
    isMine: Boolean,
) = Row(
    modifier = Modifier.padding(vertical = Dp16)
) {

    if (isMine) {
        Spacer(modifier = Modifier.weight(1f))
    }

    Box(
        modifier = Modifier
            .background(
                if (isMine) MangoGramTheme.colorScheme.primaryLight else MangoGramTheme.colorScheme.primary,
                shape = RoundedCornerShape(CornerRadius12)
            )
            .padding(Dp16),
        contentAlignment = Alignment.BottomStart
    ) {
        Text(
            text = text,
            style = MangoGramTheme.typography.bodySmall,
            color = MangoGramTheme.colorScheme.onPrimary
        )
    }
}

@Preview
@Composable
private fun ChatItemPreview() {
    MangoGramTheme {
        Column(
            Modifier
                .background(
                    MangoGramTheme.colorScheme.surfaceBright,
                    shape = RoundedCornerShape(CornerRadius12)
                )
                .fillMaxSize()
                .padding(Dp16),
        ) {

            MessageItem(
                text = "> Task :uikit:parseDebugLocalResources UP-TO-DATE\n" +
                        "> Task :uikit:generateDebugRFile UP-TO-DATE\n" +
                        "> Task :app:processDebugResources UP-TO-DATE",
                isMine = false
            )

            MessageItem(
                text = "> Task :app:compileDebugKotlin UP-TO-DATE\n",
                isMine = true
            )

            MessageItem(
                text = "> Task :uikit:parseDebugLocalResources UP-TO-DATE\n" +
                        "> Task :uikit:generateDebugRFile UP-TO-DATE\n" +
                        "> Task :app:processDebugResources UP-TO-DATE",
                isMine = false
            )

            MessageItem(
                text = "> Task :app:compileDebugKotlin UP-TO-DATE\n",
                isMine = true
            )
        }

    }
}

