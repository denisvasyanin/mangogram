package ru.disav.mangogram.features.login.presentation.code

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.disav.mangogram.R
import ru.disav.mangogram.features.login.codeTransformation
import ru.disav.mangogram.uikit.component.KitAppBar
import ru.disav.mangogram.uikit.component.KitButton
import ru.disav.mangogram.uikit.component.KitLoadingAnimation
import ru.disav.mangogram.uikit.theme.CornerRadius12
import ru.disav.mangogram.uikit.theme.Dp16
import ru.disav.mangogram.uikit.theme.Dp24
import ru.disav.mangogram.uikit.theme.Dp48
import ru.disav.mangogram.uikit.theme.MangoGramTheme

@Composable
internal fun CodeScreen(
    onNavigateToRegistration: (String) -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateBack: () -> Unit,
) {
    val viewModel: CodeScreenViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel
            .effectsFlow
            .collect {
                when (it) {
                    CodeEffects.NavigateBack -> {
                        onNavigateBack()
                    }

                    CodeEffects.NavigateToAuthorizedZone -> {
                        onNavigateToProfile()
                    }

                    is CodeEffects.NavigateToRegistration -> {
                        onNavigateToRegistration(it.phone)
                    }

                    CodeEffects.ShowError -> {
                        Toast
                            .makeText(
                                context,
                                context.getString(R.string.send_code_error_message),
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    }

                    CodeEffects.ShowMessage -> {
                        Toast
                            .makeText(
                                context,
                                context.getString(R.string.incorrect_code),
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    }
                }
            }
    }

    CodeScene(
        uiState = uiState,
        onEvent = {
            viewModel.onEvent(it)
        },
    )
}

@Composable
private fun CodeScene(
    uiState: CodeScreenUiState,
    onEvent: (CodeEvent) -> Unit,
) = Box(
    modifier = Modifier
        .fillMaxSize()
        .background(MangoGramTheme.colorScheme.surfaceBright)
) {
    Column {
        KitAppBar(
            title = stringResource(R.string.login),
            onBackButtonClick = {
                onEvent(CodeEvent.GoBack)
            }
        )

        Column(
            modifier = Modifier
                .padding(Dp16)
        ) {

            CodeSceneContent(
                onClick = { onEvent(CodeEvent.SendCode(it)) }
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

@Composable
private fun CodeSceneContent(
    onClick: (String) -> Unit,
) = Column(
    modifier = Modifier
        .fillMaxSize()
        .background(MangoGramTheme.colorScheme.surfaceBright),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    var code by rememberSaveable { mutableStateOf("") }

    Spacer(modifier = Modifier.weight(1f))

    Image(painter = painterResource(R.drawable.mango_108), contentDescription = null)

    Spacer(modifier = Modifier.height(Dp48))

    Text(text = stringResource(R.string.enter_code), style = MangoGramTheme.typography.h3)

    Spacer(modifier = Modifier.height(Dp24))

    OutlinedTextField(
        value = code,
        onValueChange = {
            if (it.isDigitsOnly()) code = it
        },
        modifier = Modifier
            .fillMaxWidth(),
        textStyle = MangoGramTheme.typography.bodyExtraLarge.copy(textAlign = TextAlign.Center),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MangoGramTheme.colorScheme.surfaceBright,
            unfocusedContainerColor = MangoGramTheme.colorScheme.surfaceBright,
            focusedBorderColor = MangoGramTheme.colorScheme.primary,
            unfocusedBorderColor = MangoGramTheme.colorScheme.outline,
        ),
        shape = RoundedCornerShape(CornerRadius12),
        visualTransformation = {
            codeTransformation(it)
        }
    )

    Spacer(modifier = Modifier.weight(1f))

    KitButton(
        text = stringResource(R.string.ready),
        onClick = { onClick(code) }
    )
}

@Preview
@Composable
private fun CodeScenePreview() {
    MangoGramTheme {
        CodeScene(
            uiState = CodeScreenUiState(
                isLoading = false,
                code = ""
            ),
            onEvent = {}
        )
    }
}