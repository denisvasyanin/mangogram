package ru.disav.mangogram.features.login.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhoneCodeApiModel(
    @Json(name = "phone")
    val phone: String,

    @Json(name = "code")
    val code: String,
)