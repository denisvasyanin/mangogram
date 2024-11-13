package ru.disav.mangogram.features.profile.presentation

sealed interface ProfileEffects {
    data object NavigateToLogin : ProfileEffects
    data object ShowError : ProfileEffects
}