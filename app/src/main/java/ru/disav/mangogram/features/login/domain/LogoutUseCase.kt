package ru.disav.mangogram.features.login.domain

import kotlinx.coroutines.flow.Flow
import ru.disav.mangogram.app.data.TokenRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(private val repository: TokenRepository) {
    operator fun invoke(): Flow<Unit> {
        return repository.logout()
    }
}