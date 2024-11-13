package ru.disav.mangogram.features.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import ru.disav.mangogram.features.profile.presentation.ProfileScreen

@Serializable
data object ProfileRoute

fun NavController.navigateToProfile() {
    this.navigate(ProfileRoute)
}

fun NavGraphBuilder.profileScreen(
    onNavigateBack: () -> Unit,
    onNavigateToLogin: () -> Unit,
) {
    composable<ProfileRoute> {
        ProfileScreen(
            onNavigateBack = onNavigateBack,
            onNavigateToLogin = onNavigateToLogin
        )
    }
}


