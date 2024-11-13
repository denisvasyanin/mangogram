package ru.disav.mangogram.features.profile.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import ru.disav.mangogram.app.di.LoggedUser
import ru.disav.mangogram.features.profile.data.ProfileApi
import ru.disav.mangogram.features.profile.data.ProfileRepositoryImpl
import ru.disav.mangogram.features.profile.domain.ProfileRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ProfileModule {

    @Binds
    @Singleton
    abstract fun bindProfileRepository(repository: ProfileRepositoryImpl): ProfileRepository

    companion object {
        @Provides
        @Singleton
        fun provideProfileApi(@LoggedUser retrofit: Retrofit): ProfileApi =
            retrofit.create(ProfileApi::class.java)
    }
}


