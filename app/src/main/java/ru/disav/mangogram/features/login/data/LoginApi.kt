package ru.disav.mangogram.features.login.data

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import ru.disav.mangogram.features.login.data.entity.CheckCodeResponseApiModel
import ru.disav.mangogram.features.login.data.entity.PhoneApiModel
import ru.disav.mangogram.features.login.data.entity.PhoneCodeApiModel

interface LoginApi {
    @POST("api/v1/users/send-auth-code/")
    suspend fun sendAuthCode(
        @Body phone: PhoneApiModel
    )

    @POST("api/v1/users/check-auth-code/")
    suspend fun checkAuthCode(
        @Body phone: PhoneCodeApiModel
    ): Response<CheckCodeResponseApiModel>
}