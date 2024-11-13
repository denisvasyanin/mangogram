package ru.disav.mangogram.features.login.presentation.code

sealed interface CodeEffects {
    data object NavigateToAuthorizedZone : CodeEffects
    data class NavigateToRegistration(val phone: String) : CodeEffects
    data object ShowError : CodeEffects
    data object ShowMessage : CodeEffects
}