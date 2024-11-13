//package com.hippoparking.login.presentation.login
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.OutlinedTextFieldDefaults
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.SpanStyle
//import androidx.compose.ui.text.buildAnnotatedString
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.text.withStyle
//import androidx.compose.ui.tooling.preview.Preview
//import com.hippoparking.login.R
//import com.hippoparking.uikit.KitIcons
//import com.hippoparking.uikit.component.KitAgreementCheckBox
//import com.hippoparking.uikit.component.KitAlertDialog
//import com.hippoparking.uikit.component.KitButton
//import com.hippoparking.uikit.theme.CornerRadius16
//import com.hippoparking.uikit.theme.Dp16
//import com.hippoparking.uikit.theme.Dp24
//import com.hippoparking.uikit.theme.HippoTheme
//import com.hippoparking.uikit.utils.keyboardAsState
//import ru.disav.mangogram.features.login.mobileNumberTransformation
//import com.hippoparking.uikit.R as UIKitR
//
//@Composable
//fun LoginScene(
//    uiState: LoginSceneUiState,
//    onSendSmsClick: ((String) -> Unit)? = null,
//    onSettingsClick: (() -> Unit)? = null,
//    onErrorDismiss: (() -> Unit)? = null,
//    onLinkClick: ((String) -> Unit)? = null,
//) {
//    val isKeyboardOpen by keyboardAsState()
//    var phoneNumber by remember { mutableStateOf(uiState.phoneNumber) }
//    val agreedStatus = rememberSaveable { mutableStateOf(uiState.agreedStatus) }
//
//    if (uiState.showError) {
//        KitAlertDialog(
//            message = uiState.errorMessage.ifEmpty {
//                stringResource(id = R.string.error_try_sign_in_later)
//            },
//            onDismiss = onErrorDismiss
//        )
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(HippoTheme.colorScheme.surfaceBright)
//            .padding(Dp16),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        if (uiState.developerOptionsEnabled) {
//            IconButton(
//                onClick = {
//                    onSettingsClick?.invoke()
//                },
//                modifier = Modifier
//                    .align(Alignment.End)
//            ) {
//                Icon(
//                    imageVector = KitIcons.Settings,
//                    contentDescription = null,
//                    tint = HippoTheme.colorScheme.primary
//                )
//            }
//        }
//
//        Spacer(modifier = Modifier.weight(1f))
//
//        if (isKeyboardOpen.not()) {
//            Image(
//                painter = painterResource(id = UIKitR.drawable.ic_hippo),
//                contentDescription = null,
//            )
//        }
//
//        val annotatedString = buildAnnotatedString {
//            append(stringResource(id = R.string.welcome_to))
//            withStyle(style = SpanStyle(HippoTheme.colorScheme.primary)) {
//                append(" ")
//                append(stringResource(id = R.string.app_name))
//            }
//        }
//
//        Text(
//            text = annotatedString,
//            modifier = Modifier.padding(top = Dp16),
//            style = HippoTheme.typography.h1,
//            textAlign = TextAlign.Center
//        )
//
//        Text(
//            text = stringResource(id = R.string.enter_your_number),
//            modifier = Modifier.padding(all = Dp16),
//            color = HippoTheme.colorScheme.onSurfaceVariant,
//            style = HippoTheme.typography.bodyMedium,
//            textAlign = TextAlign.Center
//        )
//
//        OutlinedTextField(
//            value = phoneNumber,
//            onValueChange = {
//                if (it.length <= uiState.phoneLength) phoneNumber = it
//            },
//            modifier = Modifier
//                .padding(top = Dp24)
//                .fillMaxWidth(),
//            textStyle = HippoTheme.typography.bodyMedium.copy(textAlign = TextAlign.Start),
//            leadingIcon = {
//                Icon(
//                    painter = painterResource(id = UIKitR.drawable.ic_rus),
//                    tint = Color.Unspecified,
//                    contentDescription = null
//                )
//            },
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//            singleLine = true,
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedContainerColor = HippoTheme.colorScheme.primaryLight,
//                unfocusedContainerColor = HippoTheme.colorScheme.surfaceBright,
//                focusedBorderColor = HippoTheme.colorScheme.primary,
//                unfocusedBorderColor = HippoTheme.colorScheme.outline,
//            ),
//            shape = RoundedCornerShape(CornerRadius16),
//            visualTransformation = { mobileNumberTransformation(it) }
//        )
//
//        KitAgreementCheckBox(
//            modifier = Modifier.padding(Dp16),
//            agreedStatus = agreedStatus.value,
//            messageText = stringResource(id = R.string.i_agreed_start),
//            linkText = stringResource(id = R.string.i_agreed_link),
//            url = uiState.policyUrl,
//            onCheckedChange = { agreedStatus.value = it },
//            onLinkClick = { onLinkClick?.invoke(it) },
//        )
//
//        Spacer(modifier = Modifier.weight(1f))
//
//        KitButton(
//            modifier = Modifier.fillMaxWidth(),
//            text = stringResource(R.string.send_sms),
//            enabled = phoneNumber.length == uiState.phoneLength && agreedStatus.value,
//            onClick = { onSendSmsClick?.invoke(phoneNumber) },
//        )
//    }
//}
//
//@Preview
//@Composable
//fun LoginScenePreview() {
//    HippoTheme {
//        Surface {
//            LoginScene(
//                uiState = LoginSceneUiState(
//                    phoneNumber = "9655933330",
//                    developerOptionsEnabled = true
//                )
//            )
//        }
//    }
//}
