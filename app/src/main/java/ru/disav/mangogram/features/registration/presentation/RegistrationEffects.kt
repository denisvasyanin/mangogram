package ru.disav.mangogram.features.registration.presentation

sealed interface RegistrationEffects {
    data object NavigateToProfile : RegistrationEffects
    data object ShowError : RegistrationEffects
}