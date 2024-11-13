package ru.disav.mangogram.app

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable

@Serializable
object InitialRoute

fun NavGraphBuilder.initialScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToProfile: () -> Unit,
) {
    composable<InitialRoute> {
        Box {
            val viewModel: InitialViewModel = hiltViewModel()

            LaunchedEffect(Unit) {
                delay(500)
                viewModel.effectsFlow.collect {
                    when (it) {
                        false -> onNavigateToLogin()
                        true -> onNavigateToProfile()
                    }
                }
            }

            InitialScene()
        }
    }
}


