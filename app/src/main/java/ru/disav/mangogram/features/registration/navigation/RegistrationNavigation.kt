package ru.disav.mangogram.features.registration.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import ru.disav.mangogram.features.registration.presentation.RegistrationScreen

@Serializable
data class RegistrationRoute(val phone: String) {
    companion object {
        fun from(savedStateHandle: SavedStateHandle) =
            savedStateHandle.toRoute<RegistrationRoute>()
    }
}

fun NavController.navigateToRegistration(phone: String) {
    this.navigate(RegistrationRoute(phone)) {
        popUpTo(0)
    }
}

fun NavGraphBuilder.registrationScreen(
    onNavigateToProfile: () -> Unit,
) {
    composable<RegistrationRoute> {
        RegistrationScreen(
            onNavigateToProfile = onNavigateToProfile,
        )
    }
}


