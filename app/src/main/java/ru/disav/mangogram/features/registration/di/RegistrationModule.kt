package ru.disav.mangogram.features.registration.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import ru.disav.mangogram.app.di.LoggedUser
import ru.disav.mangogram.features.registration.data.RegistrationApi
import ru.disav.mangogram.features.registration.data.RegistrationRepositoryImpl
import ru.disav.mangogram.features.registration.domain.RegistrationRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RegistrationModule {

    @Binds
    @Singleton
    abstract fun bindRegistrationRepository(repository: RegistrationRepositoryImpl): RegistrationRepository

    companion object {
        @Provides
        @Singleton
        fun provideRegistrationApi(@LoggedUser retrofit: Retrofit): RegistrationApi =
            retrofit.create(RegistrationApi::class.java)
    }
}


