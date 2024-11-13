package ru.disav.mangogram.features.chat.presentation.chat

import ru.disav.mangogram.features.chat.domain.entity.ChatMessageModel

data class ChatScreenUiState(
    val messages: List<ChatMessageModel>,
    val isLoading: Boolean,
)

