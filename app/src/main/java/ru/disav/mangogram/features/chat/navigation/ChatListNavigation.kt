package ru.disav.mangogram.features.chat.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import ru.disav.mangogram.features.chat.presentation.list.ChatListScreen

@Serializable
data object ChatListRoute

fun NavController.navigateToChatList() {
    this.navigate(ChatListRoute) {
        popUpTo(0)
    }
}

fun NavGraphBuilder.chatListScreen(
    onNavigateToProfile: () -> Unit,
    onNavigateToChat: (String) -> Unit,
) {
    composable<ChatListRoute> {
        ChatListScreen(
            onNavigateToProfile = onNavigateToProfile,
            onNavigateToChat = onNavigateToChat,
        )
    }
}


