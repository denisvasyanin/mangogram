package ru.disav.mangogram.features.login.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import ru.disav.mangogram.features.login.presentation.code.CodeScreen

@Serializable
data class CodeRoute(val phone: String) {
    companion object {
        fun from(savedStateHandle: SavedStateHandle) =
            savedStateHandle.toRoute<CodeRoute>()
    }
}

fun NavController.navigateToCode(phone: String) {
    this.navigate(CodeRoute(phone))
}

fun NavGraphBuilder.codeScreen(
    onNavigateToRegistration: (String) -> Unit,
    onNavigateToProfile: () -> Unit,
) {
    composable<CodeRoute> {
        CodeScreen(
            onNavigateToRegistration = onNavigateToRegistration,
            onNavigateToProfile = onNavigateToProfile,
        )
    }
}


