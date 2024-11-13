package ru.disav.mangogram.features.profile.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.disav.mangogram.R
import ru.disav.mangogram.features.profile.domain.entity.ProfileModel
import ru.disav.mangogram.uikit.component.KitAppBar
import ru.disav.mangogram.uikit.component.KitButton
import ru.disav.mangogram.uikit.component.KitLoadingAnimation
import ru.disav.mangogram.uikit.theme.Dp16
import ru.disav.mangogram.uikit.theme.Dp24
import ru.disav.mangogram.uikit.theme.Dp4
import ru.disav.mangogram.uikit.theme.Dp8
import ru.disav.mangogram.uikit.theme.MangoGramTheme

@Composable
internal fun ProfileScreen(
    onNavigateBack: () -> Unit,
    onNavigateToLogin: () -> Unit,
) {
    val viewModel: ProfileScreenViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel
            .effectsFlow
            .collect {
                when (it) {
                    ProfileEffects.NavigateToLogin -> {
                        onNavigateToLogin()
                    }

                    ProfileEffects.ShowError -> {
                        Toast
                            .makeText(
                                context,
                                context.getString(R.string.send_code_error_message),
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    }
                }
            }
    }

    ProfileScene(
        uiState = uiState,
        onRefresh = {
            viewModel.load()
        },
        onNavigateBack = onNavigateBack,
        onLogout = {
            viewModel.logout()
        }
    )
}

@Composable
private fun ProfileScene(
    uiState: ProfileScreenUiState,
    onNavigateBack: () -> Unit,
    onRefresh: () -> Unit,
    onLogout: () -> Unit,
) = Column(
    modifier = Modifier
        .fillMaxSize()
        .background(MangoGramTheme.colorScheme.surfaceBright)
) {
    KitAppBar(
        title = stringResource(R.string.profile),
        onBackButtonClick = onNavigateBack,
        actions = {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(Dp8))
                    .padding(Dp8)
                    .size(Dp24)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false),
                    ) {
                        onLogout()
                    }
            )
        }
    )

    when (uiState) {
        ProfileScreenUiState.Failure -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(Dp16),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.there_is_error),
                        style = MangoGramTheme.typography.h2
                    )

                    Spacer(modifier = Modifier.height(Dp16))

                    KitButton(
                        text = stringResource(R.string.try_again),
                        onClick = onRefresh
                    )
                }
            }
        }

        ProfileScreenUiState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                KitLoadingAnimation()
            }
        }

        is ProfileScreenUiState.Success -> {
            Column(
                modifier = Modifier
                    .padding(Dp16),
            ) {
                Text(
                    text = stringResource(R.string.your_phone),
                    style = MangoGramTheme.typography.h2
                )

                Spacer(modifier = Modifier.height(Dp4))

                Text(
                    text = uiState.profile.phone,
                    style = MangoGramTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(Dp16))

                Text(
                    text = stringResource(R.string.name),
                    style = MangoGramTheme.typography.h2
                )

                Spacer(modifier = Modifier.height(Dp4))

                Text(
                    text = uiState.profile.name,
                    style = MangoGramTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(Dp16))

                Text(
                    text = stringResource(R.string.username),
                    style = MangoGramTheme.typography.h2
                )

                Spacer(modifier = Modifier.height(Dp4))

                Text(
                    text = uiState.profile.username,
                    style = MangoGramTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview
@Composable
private fun ProfileScenePreview() {
    MangoGramTheme {
        ProfileScene(
            uiState = ProfileScreenUiState.Success(
                profile = ProfileModel(
                    name = "Петр Петров",
                    username = "pertor",
                    phone = "+765654645645"
                )
            ),
            onRefresh = {},
            onNavigateBack = {},
            onLogout = {}
        )
    }
}
