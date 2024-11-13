package ru.disav.mangogram.features.registration.data

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import ru.disav.mangogram.features.registration.data.entity.RegistrationApiModel
import ru.disav.mangogram.features.registration.data.entity.RegistrationResponseApiModel

interface RegistrationApi {

    @POST("api/v1/users/register/")
    suspend fun register(@Body params: RegistrationApiModel): Response<RegistrationResponseApiModel>
}