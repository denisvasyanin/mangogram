package ru.disav.mangogram.features.login.presentation.phone

sealed interface PhoneEvent {

    data class SendClick(val phoneNumber: String) : PhoneEvent
}