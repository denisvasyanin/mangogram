package ru.disav.mangogram.app.data

import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TokenRepository @Inject constructor(
    private val dataStore: TokenDataSource,
    private val api: TokenApi
) {
    suspend fun getAccessToken() = dataStore.getTokenModel()?.authToken

    suspend fun refreshToken(): String? {
        val refreshToken = dataStore.getTokenModel()?.refreshToken ?: return null
        val response = api.refreshToken(RefreshTokenApiModel(refreshToken))
        dataStore.setTokenModel(
            TokenModel(
                authToken = response.accessToken,
                refreshToken = response.refreshToken
            )
        )

        return response.accessToken
    }

    fun isAuthorized() = dataStore.isAuthorized()

    fun logout() = flow {
        dataStore.clear()
        emit(Unit)
    }

}