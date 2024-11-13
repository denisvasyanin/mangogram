package ru.disav.mangogram.features.profile.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProfileDataApiModel(
    @Json(name = "profile_data")
    val profileData: ProfileApiModel
)