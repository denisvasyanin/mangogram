package ru.disav.mangogram.features.chat.presentation.list

import androidx.compose.runtime.Immutable
import ru.disav.mangogram.features.chat.domain.entity.ChatModel

@Immutable
data class ChatListScreenUiState(
    val chats: List<ChatModel>,
    val isLoading: Boolean,
)

