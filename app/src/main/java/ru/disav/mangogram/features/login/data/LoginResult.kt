package ru.disav.mangogram.features.login.data

sealed interface LoginResult {
    data class Success(val userExists: Boolean) : LoginResult
    data object WrongCode : LoginResult
}