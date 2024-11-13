package ru.disav.mangogram.features.login.domain

import javax.inject.Inject

class CheckAuthCodeUseCase @Inject constructor(private val repository: LoginRepository) {
    suspend operator fun invoke(phone: String, code: String) =
        repository.checkAuthCode(phone, code)

}