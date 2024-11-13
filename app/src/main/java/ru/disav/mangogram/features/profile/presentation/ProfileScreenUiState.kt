package ru.disav.mangogram.features.profile.presentation

import ru.disav.mangogram.features.profile.domain.entity.ProfileModel

sealed class ProfileScreenUiState {
    data object Loading : ProfileScreenUiState()
    data class Success(val profile: ProfileModel) : ProfileScreenUiState()
    data object Failure : ProfileScreenUiState()
}