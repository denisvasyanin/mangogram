package ru.disav.mangogram.features.profile.domain

import kotlinx.coroutines.flow.Flow
import ru.disav.mangogram.features.profile.domain.entity.ProfileModel

interface ProfileRepository {
    suspend fun getCurrentProfile(): Flow<ProfileModel>
}