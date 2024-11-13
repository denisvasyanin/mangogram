package ru.disav.mangogram.features.registration.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.disav.mangogram.app.data.TokenDataSource
import ru.disav.mangogram.app.data.TokenModel
import ru.disav.mangogram.features.registration.data.entity.RegistrationApiModel
import ru.disav.mangogram.features.registration.domain.RegistrationRepository
import ru.disav.mangogram.features.registration.domain.RegistrationResult
import java.net.HttpURLConnection
import javax.inject.Inject

class RegistrationRepositoryImpl @Inject constructor(
    private val api: RegistrationApi,
    private val tokenDataSource: TokenDataSource
) : RegistrationRepository {

    override suspend fun register(
        phone: String,
        name: String,
        username: String
    ): Flow<RegistrationResult> =
        flow {
            val response = api.register(
                RegistrationApiModel(
                    phone = phone,
                    username = username,
                    name = name
                )
            )

            when (response.code()) {
                HttpURLConnection.HTTP_CREATED -> {
                    val body = checkNotNull(response.body())

                    tokenDataSource.setTokenModel(
                        TokenModel(
                            authToken = body.accessToken,
                            refreshToken = body.refreshToken
                        )
                    )
                    emit(RegistrationResult.Success)
                }

                HttpURLConnection.HTTP_NOT_FOUND -> {
                    val errorMessage = response.message()
                    emit(RegistrationResult.Failure(errorMessage))
                }

                else -> {
                    emit(RegistrationResult.Failure("Unknown error"))
                }
            }
        }

}

