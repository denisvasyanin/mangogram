package ru.disav.mangogram.features.profile.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ProfileApiModel(
    @Json(name = "name")
    val name: String,

    @Json(name = "username")
    val username: String,

    @Json(name = "phone")
    val phone: String,
)