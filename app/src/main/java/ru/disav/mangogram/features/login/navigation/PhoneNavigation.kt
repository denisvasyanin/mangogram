package ru.disav.mangogram.features.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import ru.disav.mangogram.features.login.presentation.phone.PhoneScreen

@Serializable
data object PhoneRoute

fun NavController.navigateToPhone() {
    this.navigate(PhoneRoute) {
        popUpTo(0)
    }
}

fun NavGraphBuilder.phoneScreen(
    onNavigateToCode: (String) -> Unit,
) {
    composable<PhoneRoute> {
        PhoneScreen(
            onNavigateToCode = onNavigateToCode,
        )
    }
}


