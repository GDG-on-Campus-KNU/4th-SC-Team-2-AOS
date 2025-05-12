package com.example.soop.chat.data

data class CustomChatbotData(
    val name: String = "",
    val description: String = "",
    val empathyLevel: String = "",
    val tone: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
