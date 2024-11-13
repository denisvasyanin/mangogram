package ru.disav.mangogram.features.registration.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.disav.mangogram.R
import ru.disav.mangogram.uikit.component.KitAppBar
import ru.disav.mangogram.uikit.component.KitButton
import ru.disav.mangogram.uikit.component.KitLoadingAnimation
import ru.disav.mangogram.uikit.component.KitTextField
import ru.disav.mangogram.uikit.theme.Dp16
import ru.disav.mangogram.uikit.theme.Dp24
import ru.disav.mangogram.uikit.theme.MangoGramTheme

@Composable
internal fun RegistrationScreen(
    onNavigateToProfile: () -> Unit,
) {
    val viewModel: RegistrationScreenViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel
            .effectsFlow
            .collect {
                when (it) {
                    is RegistrationEffects.NavigateToProfile -> {
                        onNavigateToProfile()
                    }

                    RegistrationEffects.ShowError -> {
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

    RegistrationScreen(
        uiState = uiState,
        onEvent = {
            viewModel.onEvent(it)
        },
    )
}

@Composable
private fun RegistrationScreen(
    uiState: RegistrationScreenUiState,
    onEvent: (RegistrationEvent) -> Unit
) = Box(
    modifier = Modifier
        .fillMaxSize()
        .background(MangoGramTheme.colorScheme.surfaceBright)
) {
    Column {
        KitAppBar(title = stringResource(R.string.registration))

        Column(
            modifier = Modifier
                .padding(Dp16)
        ) {

            Text(text = stringResource(R.string.your_phone), style = MangoGramTheme.typography.h3)

            Spacer(modifier = Modifier.height(Dp16))

            Text(text = uiState.phone, style = MangoGramTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(Dp24))

            var username by rememberSaveable { mutableStateOf("") }
            val usernameRegex = Regex("[A-Za-z0-9\\-_]{0,26}")

            Text(
                text = stringResource(R.string.enter_username),
                style = MangoGramTheme.typography.h3
            )

            Spacer(modifier = Modifier.height(Dp16))

            KitTextField(
                modifier = Modifier.fillMaxWidth(),
                value = username,
                onValueChange = {
                    if (usernameRegex.matches(it)) {
                        username = it
                    }
                }
            )

            Spacer(modifier = Modifier.height(Dp24))

            var name by rememberSaveable { mutableStateOf("") }
            val nameRegex = Regex("[A-Za-zА-Яа-я \\-]{0,30}")

            Text(text = stringResource(R.string.enter_name), style = MangoGramTheme.typography.h3)

            Spacer(modifier = Modifier.height(Dp16))

            KitTextField(
                modifier = Modifier.fillMaxWidth(),
                value = name,
                onValueChange = {
                    if (nameRegex.matches(it)) {
                        name = it
                    }
                }
            )

            Spacer(modifier = Modifier.weight(1f))

            KitButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.register),
                onClick = { onEvent(RegistrationEvent.Register(username, name)) },
                enabled = name.isNotEmpty() && username.isNotEmpty()
            )
        }
    }
    if (uiState.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White.copy(alpha = 0.8f)),
            contentAlignment = Alignment.Center
        ) {
            KitLoadingAnimation()
        }
    }
}

@Preview
@Composable
private fun RegistrationScreenPreview() {
    MangoGramTheme {
        RegistrationScreen(
            uiState = RegistrationScreenUiState(
                isLoading = false,
                phone = "+79655922221",
            ),
            onEvent = {},
        )
    }
}