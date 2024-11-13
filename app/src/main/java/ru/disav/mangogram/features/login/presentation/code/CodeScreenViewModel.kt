package ru.disav.mangogram.features.login.presentation.code

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.disav.mangogram.features.login.data.LoginResult
import ru.disav.mangogram.features.login.domain.CheckAuthCodeUseCase
import ru.disav.mangogram.features.login.navigation.CodeRoute
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CodeScreenViewModel @Inject constructor(
    private val checkAuthCodeUseCase: CheckAuthCodeUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val phone = CodeRoute.from(savedStateHandle).phone

    private val _uiState = MutableStateFlow(
        CodeScreenUiState(
            isLoading = false,
            code = ""
        )
    )
    val uiState = _uiState.asStateFlow()

    private val effectsChannel = Channel<CodeEffects>()
    val effectsFlow = effectsChannel.receiveAsFlow()

    fun onEvent(event: CodeEvent) {
        when (event) {
            is CodeEvent.SendCode -> viewModelScope.launch {
                checkAuthCodeUseCase(phone = phone, code = event.code)
                    .catch {
                        Timber.e(it)
                        _uiState.update { it.copy(isLoading = false) }
                        effectsChannel.send(CodeEffects.ShowError)
                    }
                    .onStart {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                    .collect { loginResult ->
                        when (loginResult) {
                            is LoginResult.Success -> {
                                if (loginResult.userExists) {
                                    effectsChannel.send(CodeEffects.NavigateToAuthorizedZone)
                                } else {
                                    effectsChannel.send(CodeEffects.NavigateToRegistration(phone))
                                }
                            }

                            LoginResult.WrongCode -> {
                                _uiState.update { it.copy(isLoading = false) }
                                effectsChannel.send(CodeEffects.ShowMessage)
                            }
                        }
                    }
            }

            CodeEvent.GoBack -> {
                viewModelScope.launch {
                    effectsChannel.send(CodeEffects.NavigateBack)
                }
            }
        }
    }
}