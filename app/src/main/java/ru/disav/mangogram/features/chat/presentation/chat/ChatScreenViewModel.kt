package ru.disav.mangogram.features.chat.presentation.chat

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.disav.mangogram.features.chat.domain.GetChatMessagesUseCase
import ru.disav.mangogram.features.chat.navigation.ChatRoute
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ChatScreenViewModel @Inject constructor(
    private val getChatMessagesUseCase: GetChatMessagesUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val chatId = ChatRoute.from(savedStateHandle).chatId

    private val _uiState = MutableStateFlow(
        ChatScreenUiState(
            isLoading = false,
            messages = emptyList()
        )
    )
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getChatMessagesUseCase(chatId)
                .catch {
                    _uiState.update { it.copy(isLoading = false) }
                    Timber.e(it)
                }
                .onStart {
                    _uiState.update { it.copy(isLoading = true) }
                }
                .collect { messages ->
                    _uiState.update {
                        it.copy(
                            messages = messages,
                            isLoading = false
                        )
                    }
                }
        }
    }
}