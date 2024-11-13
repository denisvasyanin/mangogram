package ru.disav.mangogram.app.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.disav.mangogram.app.data.TokenModel
import ru.disav.mangogram.app.data.TokenSerializer
import javax.inject.Singleton

private const val USER_DATA = "user_data.json"

@InstallIn(SingletonComponent::class)
@Module
object DataStoreModule {

    private val Context.dataStore by dataStore(USER_DATA, TokenSerializer)

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext appContext: Context): DataStore<TokenModel> {
        return appContext.dataStore
    }
}

