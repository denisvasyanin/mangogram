package ru.disav.mangogram.features.chat.domain.entity

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import ru.disav.mangogram.uikit.R

data class ChatModel(
    val id: String,
    val image: Bitmap,
    val title: String,
    val message: String,
    val time: String
) {
    companion object {
        fun previewModel(resources: Resources) = ChatModel(
            id = "id",
            image = BitmapFactory.decodeResource(
                resources,
                R.drawable.ic_profile
            ),
            title = "Text",
            message = "Text",
            time = "Time"
        )
    }
}