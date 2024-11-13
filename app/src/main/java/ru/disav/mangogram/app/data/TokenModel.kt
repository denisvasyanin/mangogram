package ru.disav.mangogram.app.data

import kotlinx.serialization.Serializable

@Serializable
data class  TokenModel(
    val authToken: String?,
    val refreshToken: String?,
)