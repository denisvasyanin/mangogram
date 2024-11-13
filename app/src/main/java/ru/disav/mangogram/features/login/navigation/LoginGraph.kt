package ru.disav.mangogram.features.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import kotlinx.serialization.Serializable
import ru.disav.mangogram.features.registration.navigation.registrationScreen

@Serializable
private object LoginGraphRoute

fun NavGraphBuilder.loginGraph(
    onNavigateToCode: (String) -> Unit,
    onNavigateToRegistration: (String) -> Unit,
    onNavigateToProfile: () -> Unit,
) =
    navigation<LoginGraphRoute>(startDestination = PhoneRoute::class) {
        phoneScreen(
            onNavigateToCode = onNavigateToCode
        )
        codeScreen(
            onNavigateToRegistration = onNavigateToRegistration,
            onNavigateToProfile = onNavigateToProfile
        )
        registrationScreen(
            onNavigateToProfile = onNavigateToProfile
        )
    }
