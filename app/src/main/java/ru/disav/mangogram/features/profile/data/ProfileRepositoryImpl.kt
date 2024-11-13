package ru.disav.mangogram.features.profile.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.disav.mangogram.features.profile.domain.ProfileRepository
import ru.disav.mangogram.features.profile.domain.entity.ProfileModel
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val api: ProfileApi,
) : ProfileRepository {
    override suspend fun getCurrentProfile(): Flow<ProfileModel> = flow {
        val response = api.getCurrentUser()

        emit(
            ProfileModel(
                name = response.profileData.name,
                username = response.profileData.username,
                phone = response.profileData.phone,
            )
        )
    }

}

