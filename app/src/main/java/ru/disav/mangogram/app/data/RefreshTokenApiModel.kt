package ru.disav.mangogram.app.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RefreshTokenApiModel(
    @Json(name = "refresh_token")
    val refreshToken: String
)

