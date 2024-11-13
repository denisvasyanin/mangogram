package ru.disav.mangogram.app.di

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import ru.disav.mangogram.app.data.TokenRepository
import java.net.HttpURLConnection
import javax.inject.Inject

class AccessTokenInterceptor @Inject constructor(
    private val tokenRepository: TokenRepository
) : Interceptor {
    private val mutex = Mutex()

    override fun intercept(chain: Interceptor.Chain): Response {
        return runBlocking {
            val initialAccessToken =
                tokenRepository.getAccessToken() ?: return@runBlocking chain.proceed(
                    chain.request()
                )
            val request = newRequestWithAccessToken(chain.request(), initialAccessToken)
            val response = chain.proceed(request)

            if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                val newAccessToken = mutex.withLock {
                    try {
                        val token = tokenRepository.getAccessToken()
                        if (token == initialAccessToken) {
                            tokenRepository.refreshToken()
                        } else {
                            token
                        }
                    } catch (_: Throwable) {
                        null
                    }
                }

                if (newAccessToken == null) {
                    return@runBlocking response
                }
                response.close()
                chain.proceed(newRequestWithAccessToken(request, newAccessToken))

            } else {
                response
            }
        }
    }

    private fun newRequestWithAccessToken(request: Request, accessToken: String): Request {
        return request.newBuilder()
            .header("Authorization", "Bearer $accessToken")
            .build()
    }
}