package ru.disav.mangogram.features.login.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhoneApiModel(
    @Json(name = "phone")
    val phone: String
)

