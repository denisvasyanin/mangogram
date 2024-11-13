package ru.disav.mangogram.features.chat.presentation.list

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
import ru.disav.mangogram.features.chat.domain.GetChatsUseCase
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ChatListScreenViewModel @Inject constructor(
    private val getChatsUseCase: GetChatsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        ChatListScreenUiState(
            isLoading = false,
            chats = emptyList()
        )
    )
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getChatsUseCase()
                .catch {
                    _uiState.update { it.copy(isLoading = false) }
                    Timber.e(it)
                }
                .onStart {
                    _uiState.update { it.copy(isLoading = true) }
                }
                .collect { chats ->
                    _uiState.update {
                        it.copy(
                            chats = chats,
                            isLoading = false
                        )
                    }
                }
        }
    }
}