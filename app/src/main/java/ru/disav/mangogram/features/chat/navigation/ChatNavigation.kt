package ru.disav.mangogram.features.chat.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import ru.disav.mangogram.features.chat.presentation.chat.ChatScreen


@Serializable
data class ChatRoute(val chatId: String) {
    companion object {
        fun from(savedStateHandle: SavedStateHandle) =
            savedStateHandle.toRoute<ChatRoute>()
    }
}

fun NavController.navigateToChat(chatId: String) {
    this.navigate(ChatRoute(chatId))
}

fun NavGraphBuilder.chatScreen(
    onNavigateBack: () -> Unit,
) {
    composable<ChatRoute> {
        ChatScreen(
            onNavigateBack = onNavigateBack,
        )
    }
}

