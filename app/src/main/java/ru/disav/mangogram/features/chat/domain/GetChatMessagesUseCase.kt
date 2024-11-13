package ru.disav.mangogram.features.chat.domain

import kotlinx.coroutines.flow.Flow
import ru.disav.mangogram.features.chat.domain.entity.ChatMessageModel
import javax.inject.Inject

class GetChatMessagesUseCase @Inject constructor(private val repository: ChatRepository) {
    suspend operator fun invoke(chatId: String): Flow<List<ChatMessageModel>> {
        return repository.getMessages(chatId)
    }
}

