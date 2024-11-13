package ru.disav.mangogram.features.profile.presentation

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
import ru.disav.mangogram.features.login.domain.LogoutUseCase
import ru.disav.mangogram.features.profile.domain.GetCurrentProfileUseCase
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val getCurrentProfileUseCase: GetCurrentProfileUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<ProfileScreenUiState>(
        ProfileScreenUiState.Loading
    )
    val uiState = _uiState.asStateFlow()

    private val effectsChannel = Channel<ProfileEffects>()
    val effectsFlow = effectsChannel.receiveAsFlow()

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            getCurrentProfileUseCase()
                .catch {
                    Timber.e(it)
                    _uiState.update { ProfileScreenUiState.Failure }
                }
                .onStart {
                    _uiState.update { ProfileScreenUiState.Loading }
                }
                .collect { profile ->
                    _uiState.update {
                        ProfileScreenUiState.Success(profile)
                    }
                }
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase()
                .catch {
                    Timber.e(it)
                    effectsChannel.send(ProfileEffects.ShowError)
                }
                .collect { profile ->
                    effectsChannel.send(ProfileEffects.NavigateToLogin)
                }

        }
    }

}

