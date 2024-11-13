package ru.disav.mangogram.features.chat.data.entity

data class ChatApiModel(
    val id: String,
    val image: String,
    val title: String,
    val message: String,
    val time: String
)