package ru.disav.mangogram.features.registration.domain

import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val repository: RegistrationRepository) {
    suspend operator fun invoke(phone: String, name: String, username: String) =
        repository.register(phone, name, username)
}

