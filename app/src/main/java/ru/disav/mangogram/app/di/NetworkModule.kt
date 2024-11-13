package ru.disav.mangogram.app.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.disav.mangogram.app.data.TokenApi
import ru.disav.mangogram.features.login.data.LoginApi
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrl

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NotLoggedUser

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LoggedUser

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    @BaseUrl
    fun provideUrl(): String = "https://plannerok.ru"

    @Singleton
    @Provides
    @LoggedUser
    fun provideUserRetrofit(
        @BaseUrl baseUrl: String,
        interceptor: AccessTokenInterceptor
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .baseUrl(baseUrl)
            .client(
                OkHttpClient
                    .Builder()
                    .addInterceptor(interceptor)
                    .build()
            ).build()

    @Singleton
    @Provides
    @NotLoggedUser
    fun provideRetrofit(@BaseUrl baseUrl: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
            )
        )
        .baseUrl(baseUrl)
        .client(
            OkHttpClient.Builder().build()
        ).build()

    @Provides
    @Singleton
    fun provideAuthApi(@NotLoggedUser retrofit: Retrofit): LoginApi =
        retrofit.create(LoginApi::class.java)

    @Provides
    @Singleton
    fun provideTokenApi(@NotLoggedUser retrofit: Retrofit): TokenApi =
        retrofit.create(TokenApi::class.java)
}


