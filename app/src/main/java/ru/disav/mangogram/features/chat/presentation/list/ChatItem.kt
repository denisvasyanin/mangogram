package ru.disav.mangogram.features.chat.presentation.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import ru.disav.mangogram.features.chat.domain.entity.ChatModel
import ru.disav.mangogram.uikit.theme.Dp16
import ru.disav.mangogram.uikit.theme.Dp40
import ru.disav.mangogram.uikit.theme.Dp8
import ru.disav.mangogram.uikit.theme.MangoGramTheme

@Composable
fun ChatItem(
    chatModel: ChatModel,
    onClick: () -> Unit,
) = Row(
    modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onClick()
        }
        .padding(horizontal = Dp16),
    verticalAlignment = Alignment.CenterVertically
) {
    Image(
        bitmap = chatModel.image.asImageBitmap(),
        contentDescription = "ChatItem",
        modifier = Modifier
            .size(Dp40)
            .clip(CircleShape)
    )

    Spacer(modifier = Modifier.width(Dp16))

    Column {
        Spacer(modifier = Modifier.height(Dp8))

        Row {
            Text(
                text = chatModel.title,
                style = MangoGramTheme.typography.h4
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = chatModel.time,
                style = MangoGramTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(Dp8))

        Text(
            text = chatModel.message,
            style = MangoGramTheme.typography.bodySmall
        )

        Spacer(modifier = Modifier.height(Dp8))

        HorizontalDivider(modifier = Modifier.fillMaxWidth())
    }
}


@Preview
@Composable
private fun ChatItemPreview() {
    MangoGramTheme {
        val resources = LocalContext.current.resources

        ChatItem(
            chatModel = ChatModel.previewModel(resources),
            onClick = {}
        )
    }
}

