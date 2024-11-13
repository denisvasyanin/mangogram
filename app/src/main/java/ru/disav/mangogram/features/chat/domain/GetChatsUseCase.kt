package ru.disav.mangogram.features.chat.domain

import kotlinx.coroutines.flow.Flow
import ru.disav.mangogram.features.chat.domain.entity.ChatModel
import javax.inject.Inject

class GetChatsUseCase @Inject constructor(private val repository: ChatRepository) {
    suspend operator fun invoke(): Flow<List<ChatModel>> {
        return repository.getChats()
    }
}

