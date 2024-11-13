package ru.disav.mangogram.features.login.presentation.phone

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.disav.mangogram.R
import ru.disav.mangogram.features.login.mobileNumberTransformation
import ru.disav.mangogram.uikit.component.KitAppBar
import ru.disav.mangogram.uikit.component.KitButton
import ru.disav.mangogram.uikit.component.KitLoadingAnimation
import ru.disav.mangogram.uikit.theme.CornerRadius12
import ru.disav.mangogram.uikit.theme.Dp120
import ru.disav.mangogram.uikit.theme.Dp16
import ru.disav.mangogram.uikit.theme.Dp24
import ru.disav.mangogram.uikit.theme.Dp48
import ru.disav.mangogram.uikit.theme.MangoGramTheme

private const val PHONE_LENGTH = 10

@Composable
internal fun PhoneScreen(
    onNavigateToCode: (String) -> Unit,
) {
    val viewModel: PhoneScreenViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel
            .effectsFlow
            .collect {
                when (it) {
                    is PhoneEffects.NavigateToCode -> {
                        onNavigateToCode(it.phone)
                    }

                    PhoneEffects.ShowError -> {
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

    PhoneScene(
        uiState = uiState,
        onEvent = {
            viewModel.onEvent(it)
        },
    )
}

@Composable
private fun PhoneScene(
    uiState: PhoneScreenUiState,
    onEvent: (PhoneEvent) -> Unit
) = Box(
    modifier = Modifier
        .fillMaxSize()
        .background(MangoGramTheme.colorScheme.surfaceBright)
) {
    Column {
        KitAppBar(title = stringResource(R.string.login))

        Column(
            modifier = Modifier
                .padding(Dp16)
        ) {

            PhoneSceneContent(
                phone = uiState.phone,
                onClick = {
                    onEvent(PhoneEvent.SendClick(it))
                }
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
private fun PhoneSceneContent(
    phone: String,
    onClick: (String) -> Unit,
) = Column(
    modifier = Modifier
        .fillMaxSize()
        .background(MangoGramTheme.colorScheme.surfaceBright),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    var phoneNumber by rememberSaveable { mutableStateOf(phone) }

    Spacer(modifier = Modifier.weight(1f))

    Box(modifier = Modifier.size(Dp120), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(R.drawable.mango_108),
            contentDescription = null,
        )
    }

    Spacer(modifier = Modifier.height(Dp48))

    Text(text = stringResource(R.string.enter_phone), style = MangoGramTheme.typography.h3)

    Spacer(modifier = Modifier.height(Dp24))

    OutlinedTextField(
        value = phoneNumber,
        onValueChange = {
            if (it.length <= PHONE_LENGTH) phoneNumber = it
        },
        modifier = Modifier
            .fillMaxWidth(),
        textStyle = MangoGramTheme.typography.bodyLarge.copy(textAlign = TextAlign.Start),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_rus),
                tint = Color.Unspecified,
                contentDescription = null
            )
        },
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
            mobileNumberTransformation(it)
        }
    )

    Spacer(modifier = Modifier.weight(1f))

    KitButton(
        text = stringResource(R.string.ready),
        onClick = { onClick(phoneNumber) },
        enabled = phoneNumber.length == PHONE_LENGTH
    )
}


@Preview
@Composable
private fun PhoneScenePreview() = MangoGramTheme {
    PhoneScene(PhoneScreenUiState(isLoading = false, phone = ""), onEvent = {})
}
