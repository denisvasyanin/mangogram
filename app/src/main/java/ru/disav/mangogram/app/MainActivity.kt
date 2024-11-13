package ru.disav.mangogram.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.disav.mangogram.features.chat.navigation.navigateToChat
import ru.disav.mangogram.features.chat.navigation.navigateToChatList
import ru.disav.mangogram.features.login.navigation.loginGraph
import ru.disav.mangogram.features.login.navigation.navigateToCode
import ru.disav.mangogram.features.login.navigation.navigateToPhone
import ru.disav.mangogram.features.profile.navigation.navigateToProfile
import ru.disav.mangogram.features.profile.navigation.profileGraph
import ru.disav.mangogram.features.registration.navigation.navigateToRegistration
import ru.disav.mangogram.uikit.theme.MangoGramTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MangoGramTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = InitialRoute
                ) {
                    initialScreen(
                        onNavigateToLogin = { navController.navigateToPhone() },
                        onNavigateToProfile = { navController.navigateToChatList() }
                    )

                    loginGraph(
                        onNavigateToCode = {
                            navController.navigateToCode(it)
                        },
                        onNavigateToRegistration = {
                            navController.navigateToRegistration(it)
                        },
                        onNavigateToProfile = {
                            navController.navigateToChatList()
                        },
                        onNavigateBack = {
                            navController.popBackStack()
                        }
                    )

                    profileGraph(
                        onNavigateBack = {
                            navController.popBackStack()
                        },
                        onNavigateToProfile = {
                            navController.navigateToProfile()
                        },
                        onNavigateToChat = {
                            navController.navigateToChat(it)
                        },
                        onNavigateToLogin = {
                            navController.navigateToPhone()
                        }
                    )
                }
            }
        }
    }
}
