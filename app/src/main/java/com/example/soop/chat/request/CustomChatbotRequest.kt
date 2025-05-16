package com.example.soop.chat.request

data class CustomChatbotRequest (
    val name: String = "",
    val description: String = "",
    val empathyLevel: String = "",
    val tone: String = "",
    val image: Int = 0
)