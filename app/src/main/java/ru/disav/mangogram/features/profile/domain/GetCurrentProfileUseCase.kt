package ru.disav.mangogram.features.profile.domain

import kotlinx.coroutines.flow.Flow
import ru.disav.mangogram.features.profile.domain.entity.ProfileModel
import javax.inject.Inject

class GetCurrentProfileUseCase @Inject constructor(private val repository: ProfileRepository) {
    suspend operator fun invoke(): Flow<ProfileModel> {
        return repository.getCurrentProfile()
    }
}

