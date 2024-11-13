package ru.disav.mangogram.features.registration.domain

import kotlinx.coroutines.flow.Flow

interface RegistrationRepository {

    suspend fun register(phone: String, name: String, username: String): Flow<RegistrationResult>
}