package ru.disav.mangogram.app.data

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class TokenDataSource @Inject constructor(
    private val dataStore: DataStore<TokenModel>
) {
    fun isAuthorized() = flow {
        val token = try {
            dataStore.data.firstOrNull()?.authToken
        } catch (e: Throwable) {
            null
        }
        emit(token != null)
    }

    suspend fun getTokenModel() = try {
        dataStore.data.firstOrNull()
    } catch (e: Throwable) {
        null
    }

    suspend fun setTokenModel(tokenModel: TokenModel) = try {
        dataStore.updateData { tokenModel }
        true
    } catch (e: Exception) {
        Timber.e(e)
        false
    }

    suspend fun clear() {
        dataStore.updateData { TokenModel(null, null) }
    }
}

