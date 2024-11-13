package ru.disav.mangogram.features.registration.presentation

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
import ru.disav.mangogram.features.registration.domain.RegisterUseCase
import ru.disav.mangogram.features.registration.navigation.RegistrationRoute
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RegistrationScreenViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val args = RegistrationRoute.from(savedStateHandle)

    private val _uiState = MutableStateFlow(
        RegistrationScreenUiState(
            phone = args.phone,
            isLoading = false,
        )
    )
    val uiState = _uiState.asStateFlow()

    private val effectsChannel = Channel<RegistrationEffects>()
    val effectsFlow = effectsChannel.receiveAsFlow()

    fun onEvent(event: RegistrationEvent) {
        when (event) {
            is RegistrationEvent.Register -> {
                viewModelScope.launch {
                    registerUseCase(
                        args.phone,
                        name = event.name,
                        username = event.username
                    )
                        .catch {
                            Timber.e(it)
                            _uiState.update { it.copy(isLoading = false) }
                            effectsChannel.send(RegistrationEffects.ShowError)
                        }
                        .onStart {
                            _uiState.update { it.copy(isLoading = true) }
                        }
                        .collect {
                            effectsChannel.send(RegistrationEffects.NavigateToProfile)
                        }
                }
            }
        }
    }
}