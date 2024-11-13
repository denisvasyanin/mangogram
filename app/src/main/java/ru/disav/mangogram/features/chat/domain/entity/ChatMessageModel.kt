package ru.disav.mangogram.features.chat.domain.entity

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import ru.disav.mangogram.uikit.R as UIKitR

data class ChatMessageModel(
    val id: String,
    val image: Bitmap,
    val text: String,
    val time: String,
    val isMine: Boolean
) {
    companion object {
        fun previewModel(resources: Resources) = ChatMessageModel(
            id = "id",
            image = BitmapFactory.decodeResource(
                resources,
                UIKitR.drawable.ic_profile
            ),
            text = "Text",
            time = "Time",
            isMine = false
        )
    }
}