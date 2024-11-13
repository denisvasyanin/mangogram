package ru.disav.mangogram.features.chat.presentation.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.disav.mangogram.R
import ru.disav.mangogram.features.chat.domain.entity.ChatMessageModel
import ru.disav.mangogram.uikit.component.KitAppBar
import ru.disav.mangogram.uikit.component.KitTextField
import ru.disav.mangogram.uikit.theme.Dp16
import ru.disav.mangogram.uikit.theme.Dp24
import ru.disav.mangogram.uikit.theme.Dp56
import ru.disav.mangogram.uikit.theme.Dp72
import ru.disav.mangogram.uikit.theme.Dp8
import ru.disav.mangogram.uikit.theme.MangoGramTheme

@Composable
internal fun ChatScreen(
    onNavigateBack: () -> Unit,
) {
    val viewModel: ChatScreenViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ChatScene(
        uiState = uiState,
        onNavigateBack = onNavigateBack
    )
}

@Composable
private fun ChatScene(
    uiState: ChatScreenUiState,
    onNavigateBack: () -> Unit,
) = Column(
    modifier = Modifier
        .fillMaxSize()
        .background(MangoGramTheme.colorScheme.surfaceBright)
) {
    KitAppBar(
        title = stringResource(R.string.chat),
        onBackButtonClick = onNavigateBack,
    )

    val chatListState = rememberLazyListState(
        initialFirstVisibleItemIndex = uiState.messages.size - 1
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(bottom = Dp56),
            contentPadding = PaddingValues(Dp16),
            state = chatListState,

            ) {
            items((uiState.messages)) { item ->
                MessageItem(
                    text = item.text,
                    isMine = item.isMine
                )
            }
        }

        Row(
            Modifier
                .background(MangoGramTheme.colorScheme.surfaceDim)
                .fillMaxWidth()
                .height(Dp72)
                .padding(Dp8)
                .align(Alignment.BottomCenter),
            verticalAlignment = Alignment.CenterVertically
        ) {

            KitTextField(
                Modifier.weight(1f),
                value = "",
                placeholderText = stringResource(R.string.enter_message),
            )

            Spacer(modifier = Modifier.width(Dp16))

            Icon(
                imageVector = Icons.Filled.Send,
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(Dp8))
                    .padding(Dp8)
                    .size(Dp24)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false),
                    ) {

                    }
            )
        }
    }
}

@Preview
@Composable
private fun ChatScenePreview() {
    MangoGramTheme {
        val resources = LocalContext.current.resources
        ChatScene(
            uiState = ChatScreenUiState(
                isLoading = false,
                messages = (1..40).toList().map { ChatMessageModel.previewModel(resources) }
            ),
            onNavigateBack = {}
        )
    }
}
