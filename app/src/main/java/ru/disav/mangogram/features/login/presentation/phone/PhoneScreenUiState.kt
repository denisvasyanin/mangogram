package ru.disav.mangogram.features.login.presentation.phone

data class PhoneScreenUiState(
    val isLoading: Boolean,
    val phone: String,
) {
    fun getNumber() = "+7${phone}"
}