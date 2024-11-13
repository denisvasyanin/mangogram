package ru.disav.mangogram.features.login.domain

import kotlinx.coroutines.flow.Flow
import ru.disav.mangogram.app.data.TokenRepository
import javax.inject.Inject

class CheckAuthorizedUseCase @Inject constructor(private val repository: TokenRepository) {
    suspend operator fun invoke(): Flow<Boolean> {
        return repository.isAuthorized()
    }

}