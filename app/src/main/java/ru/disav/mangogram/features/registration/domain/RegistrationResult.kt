package ru.disav.mangogram.features.registration.domain

sealed interface RegistrationResult {
    data object Success : RegistrationResult
    data class Failure(val message: String) : RegistrationResult
}