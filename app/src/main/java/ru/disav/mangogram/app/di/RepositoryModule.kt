package ru.disav.mangogram.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.disav.mangogram.features.login.data.LoginRepositoryImpl
import ru.disav.mangogram.features.login.domain.LoginRepository
import ru.disav.mangogram.features.registration.data.RegistrationRepositoryImpl
import ru.disav.mangogram.features.registration.domain.RegistrationRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindLoginRepository(repository: LoginRepositoryImpl): LoginRepository
}