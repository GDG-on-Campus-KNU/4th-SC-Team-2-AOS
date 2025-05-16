package com.example.soop.chat.response

data class ChatbotListResponse(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val empathyLevel: String = "",
    val tone: String = "",
    val image: Int
)