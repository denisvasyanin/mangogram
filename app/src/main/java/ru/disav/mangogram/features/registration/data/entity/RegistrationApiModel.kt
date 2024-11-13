package ru.disav.mangogram.features.registration.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegistrationApiModel(
    @Json(name = "phone")
    val phone: String,

    @Json(name = "username")
    val username: String,

    @Json(name = "name")
    val name: String,
)