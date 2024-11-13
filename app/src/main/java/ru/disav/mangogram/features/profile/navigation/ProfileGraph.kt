package ru.disav.mangogram.features.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import kotlinx.serialization.Serializable
import ru.disav.mangogram.features.chat.navigation.chatListScreen
import ru.disav.mangogram.features.chat.navigation.chatScreen

@Serializable
object ProfileGraphRoute

fun NavGraphBuilder.profileGraph(
    onNavigateBack: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToLogin: () -> Unit,
    onNavigateToChat: (String) -> Unit,
) =
    navigation<ProfileGraphRoute>(startDestination = ProfileRoute::class) {
        profileScreen(
            onNavigateBack = onNavigateBack,
            onNavigateToLogin = onNavigateToLogin
        )

        chatScreen(onNavigateBack = onNavigateBack)

        chatListScreen(
            onNavigateToProfile = onNavigateToProfile,
            onNavigateToChat = onNavigateToChat
        )
    }
