package ru.disav.mangogram.features.login.presentation.phone

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
import ru.disav.mangogram.features.login.domain.SendAuthCodeUseCase
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PhoneScreenViewModel @Inject constructor(
    private val sendAuthCodeUseCase: SendAuthCodeUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        PhoneScreenUiState(
            isLoading = false,
            phone = "",
        )
    )
    val uiState = _uiState.asStateFlow()

    private val effectsChannel = Channel<PhoneEffects>()
    val effectsFlow = effectsChannel.receiveAsFlow()

    fun onEvent(event: PhoneEvent) {
        when (event) {
            is PhoneEvent.SendClick -> {
                viewModelScope.launch {
                    _uiState.update { it.copy(phone = event.phoneNumber) }
                    sendAuthCodeUseCase(_uiState.value.getNumber())
                        .catch {
                            _uiState.update { it.copy(isLoading = false) }
                            effectsChannel.send(PhoneEffects.ShowError)
                            Timber.e(it)
                        }
                        .onStart {
                            _uiState.update { it.copy(isLoading = true) }
                        }
                        .collect {
                            _uiState.update {
                                it.copy(isLoading = false)
                            }
                            effectsChannel.send(PhoneEffects.NavigateToCode(_uiState.value.getNumber()))
                        }
                }
            }
        }
    }
}