package ru.disav.mangogram.features.registration.presentation

sealed interface RegistrationEvent {
    data class Register(val username: String, val name: String) :
        RegistrationEvent
}