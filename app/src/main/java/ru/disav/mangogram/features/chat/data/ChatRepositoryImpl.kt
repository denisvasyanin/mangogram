package ru.disav.mangogram.features.chat.data

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.disav.mangogram.features.chat.data.entity.ChatApiModel
import ru.disav.mangogram.features.chat.data.entity.ChatMessageApiModel
import ru.disav.mangogram.features.chat.domain.ChatRepository
import ru.disav.mangogram.features.chat.domain.entity.ChatMessageModel
import ru.disav.mangogram.features.chat.domain.entity.ChatModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Base64
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
) : ChatRepository {
    override suspend fun getChats(): Flow<List<ChatModel>> = flow {
        val mapped = fakeApiChats.map { apiModel ->
            ChatModel(
                id = apiModel.id,
                image = apiModel.image.toBitmap(),
                title = apiModel.title,
                message = apiModel.message,
                time = apiModel.time,
            )
        }
        emit(mapped)
    }

    override suspend fun getMessages(chatId: String): Flow<List<ChatMessageModel>> = flow {
        val mapped = fakeApiMessages.map { apiModel ->
            ChatMessageModel(
                id = apiModel.id,
                image = apiModel.image.toBitmap(),
                text = apiModel.text,
                time = apiModel.time,
                isMine = (1..10).random() % 2 == 0
            )
        }
        emit(mapped)
    }

    private fun String.toBitmap(): Bitmap {
        val imageBytes = Base64.getDecoder().decode(this)
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }

    companion object {

        private val images = listOf(
            "iVBORw0KGgoAAAANSUhEUgAAAQAAAAEACAIAAADTED8xAAADMElEQVR4nOzVwQnAIBQFQYXff81RUkQCOyDj1YOPnbXWPmeTRef+/3O/OyBjzh3CD95BfqICMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMO0TAAD//2Anhf4QtqobAAAAAElFTkSuQmCC"
        )

        private val fakeApiChats = (1..40).map {
            ChatApiModel(
                id = it.toString(),
                image = images.random(),
                title = "Contact $it",
                message = "Message $it",
                time = DateTimeFormatter.ofPattern("HH:ss").format(LocalDateTime.now())
            )
        }

        private val fakeApiMessages = (1..40).map {
            ChatMessageApiModel(
                id = it.toString(),
                image = images.random(),
                text = "Text for message $it",
                time = DateTimeFormatter.ofPattern("HH:ss").format(LocalDateTime.now())
            )
        }

    }
}

