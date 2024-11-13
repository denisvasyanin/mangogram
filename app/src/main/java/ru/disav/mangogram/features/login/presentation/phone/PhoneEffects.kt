package ru.disav.mangogram.features.login.presentation.phone

sealed interface PhoneEffects {
    data class NavigateToCode(val phone: String) : PhoneEffects
    data object ShowError : PhoneEffects
}