package ru.disav.mangogram.features.login.domain

import kotlinx.coroutines.flow.Flow
import ru.disav.mangogram.app.data.TokenModel
import ru.disav.mangogram.features.login.data.LoginResult

interface LoginRepository {
    suspend fun sendAuthCode(phone: String): Flow<Unit>

    suspend fun checkAuthCode(phone: String, code: String): Flow<LoginResult>
}