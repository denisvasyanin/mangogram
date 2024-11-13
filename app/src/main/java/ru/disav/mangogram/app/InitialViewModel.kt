package ru.disav.mangogram.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.disav.mangogram.features.login.domain.CheckAuthorizedUseCase
import javax.inject.Inject

@HiltViewModel
class InitialViewModel @Inject constructor(
    private val checkAuthorizedUseCase: CheckAuthorizedUseCase,
) : ViewModel() {
    private val effectsChannel = Channel<Boolean>()
    val effectsFlow = effectsChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            checkAuthorizedUseCase()
                .catch {
                    effectsChannel.send(false)
                }
                .collect {
                    effectsChannel.send(it)
                }
        }
    }
}