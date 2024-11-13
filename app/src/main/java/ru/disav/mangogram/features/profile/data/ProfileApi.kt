package ru.disav.mangogram.features.profile.data

import retrofit2.http.GET
import ru.disav.mangogram.features.profile.data.entity.ProfileDataApiModel

interface ProfileApi {
    @GET("/api/v1/users/me")
    suspend fun getCurrentUser(): ProfileDataApiModel
}