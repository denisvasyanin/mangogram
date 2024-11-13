package ru.disav.mangogram.features.chat.domain

import kotlinx.coroutines.flow.Flow
import ru.disav.mangogram.features.chat.domain.entity.ChatMessageModel
import ru.disav.mangogram.features.chat.domain.entity.ChatModel

interface ChatRepository {
    suspend fun getChats(): Flow<List<ChatModel>>
    suspend fun getMessages(chatId: String): Flow<List<ChatMessageModel>>
}