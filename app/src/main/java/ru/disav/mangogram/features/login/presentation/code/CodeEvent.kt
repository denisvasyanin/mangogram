package ru.disav.mangogram.features.login.presentation.code

sealed interface CodeEvent {

    data class SendCode(val code: String) : CodeEvent
}