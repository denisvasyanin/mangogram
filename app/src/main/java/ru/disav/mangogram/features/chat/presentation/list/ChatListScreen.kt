package ru.disav.mangogram.features.chat.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.disav.mangogram.R
import ru.disav.mangogram.features.chat.domain.entity.ChatModel
import ru.disav.mangogram.uikit.component.KitAppBar
import ru.disav.mangogram.uikit.theme.Dp16
import ru.disav.mangogram.uikit.theme.Dp2
import ru.disav.mangogram.uikit.theme.Dp24
import ru.disav.mangogram.uikit.theme.Dp8
import ru.disav.mangogram.uikit.theme.MangoGramTheme

@Composable
internal fun ChatListScreen(
    onNavigateToProfile: () -> Unit,
    onNavigateToChat: (String) -> Unit,
) {
    val viewModel: ChatListScreenViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ChatListScene(
        uiState = uiState,
        onNavigateToProfile = onNavigateToProfile,
        onNavigateToChat = onNavigateToChat
    )
}

@Composable
private fun ChatListScene(
    uiState: ChatListScreenUiState,
    onNavigateToProfile: () -> Unit,
    onNavigateToChat: (String) -> Unit,
) = Column(
    modifier = Modifier
        .fillMaxSize()
        .background(MangoGramTheme.colorScheme.surfaceBright)
) {
    KitAppBar(
        title = stringResource(R.string.chats),
        actions = {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(Dp8))
                    .padding(Dp8)
                    .size(Dp24)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false),
                    ) {
                        onNavigateToProfile()
                    }
            )
        }
    )

    LazyColumn(
        contentPadding = PaddingValues(vertical = Dp2)
    ) {
        items((uiState.chats)) { item ->
            ChatItem(
                chatModel = item,
                onClick = {
                    onNavigateToChat(item.id)
                }
            )
        }
    }
}

@Preview
@Composable
private fun ChatListScenePreview() {
    MangoGramTheme {
        val resources = LocalContext.current.resources

        ChatListScene(
            uiState = ChatListScreenUiState(
                isLoading = false,
                chats = (1..40).toList().map { ChatModel.previewModel(resources) }
            ),
            onNavigateToProfile = {},
            onNavigateToChat = {}
        )
    }
}

