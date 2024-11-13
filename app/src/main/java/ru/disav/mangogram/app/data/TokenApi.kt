package ru.disav.mangogram.app.data

import retrofit2.http.Body
import retrofit2.http.POST

interface TokenApi {
    @POST("api/v1/users/refresh-token/")
    suspend fun refreshToken(
        @Body refreshToken: RefreshTokenApiModel
    ): RefreshResponseApiModel
}

