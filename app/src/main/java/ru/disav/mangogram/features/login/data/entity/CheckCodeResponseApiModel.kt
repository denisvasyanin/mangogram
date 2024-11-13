package ru.disav.mangogram.features.login.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CheckCodeResponseApiModel(
    @Json(name = "refresh_token")
    val refreshToken: String?,

    @Json(name = "access_token")
    val accessToken: String?,

    @Json(name = "is_user_exists")
    val userExists: Boolean,
)