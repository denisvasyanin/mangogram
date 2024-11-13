package ru.disav.mangogram.features.login.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SendAuthCodeUseCase @Inject constructor(private val repository: LoginRepository) {
    suspend operator fun invoke(phone: String): Flow<Unit> {
        return repository.sendAuthCode(phone)
    }
}


