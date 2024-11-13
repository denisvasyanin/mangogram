package ru.disav.mangogram.features.login.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.disav.mangogram.app.data.TokenDataSource
import ru.disav.mangogram.app.data.TokenModel
import ru.disav.mangogram.features.login.data.entity.PhoneApiModel
import ru.disav.mangogram.features.login.data.entity.PhoneCodeApiModel
import ru.disav.mangogram.features.login.domain.LoginRepository
import java.net.HttpURLConnection
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val api: LoginApi,
    private val tokenDataSource: TokenDataSource
) : LoginRepository {

    override suspend fun sendAuthCode(phone: String): Flow<Unit> = flow {
        api.sendAuthCode(PhoneApiModel(phone))
        emit(Unit)
    }

    override suspend fun checkAuthCode(
        phone: String,
        code: String
    ): Flow<LoginResult> = flow {
        val response = api.checkAuthCode(PhoneCodeApiModel(phone, code))

        when (response.code()) {
            HttpURLConnection.HTTP_OK -> {
                val body = checkNotNull(response.body())
                if (body.userExists) {
                    val accessToken = checkNotNull(body.accessToken)
                    val refreshToken = checkNotNull(body.refreshToken)
                    tokenDataSource.setTokenModel(
                        TokenModel(
                            authToken = accessToken,
                            refreshToken = refreshToken
                        )
                    )
                }
                emit(LoginResult.Success(body.userExists))
            }

            HttpURLConnection.HTTP_NOT_FOUND -> {
                emit(LoginResult.WrongCode)
            }

            else -> {
                error("Unknown server error")
            }
        }
    }
}

