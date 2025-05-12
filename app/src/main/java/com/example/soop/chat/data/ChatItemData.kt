package com.example.soop.chat.data

import java.time.LocalDateTime

data class ChatItemData(
    val chatId: Int,
    val chatRoomId: Int,
    val content: String,
    val senderId: Int,
)