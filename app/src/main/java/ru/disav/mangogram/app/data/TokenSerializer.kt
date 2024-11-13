package ru.disav.mangogram.app.data

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object TokenSerializer : Serializer<TokenModel> {
    override val defaultValue = TokenModel(
        authToken = null,
        refreshToken = null
    )

    override suspend fun readFrom(input: InputStream): TokenModel {
        try {
            return Json.decodeFromString(
                TokenModel.serializer(),
                input.readBytes().decodeToString()
            )
        } catch (serialization: SerializationException) {
            throw CorruptionException("Unable to read UserPrefs", serialization)
        }
    }

    override suspend fun writeTo(t: TokenModel, output: OutputStream) =
        withContext(Dispatchers.IO) {
            output.write(Json.encodeToString(TokenModel.serializer(), t).encodeToByteArray())
        }
}

