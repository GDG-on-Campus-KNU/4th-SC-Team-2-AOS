package com.example.soop.chat.response

import java.time.LocalDateTime

data class ChatItemResponse(
    val chatId: String = "",
    val chatRoomId: Int = 0,
    val senderId: Int = 0,
    val content: String = "",
    val createdAt: String = ""
)

data class ChatItemListWrapper(
    val chats: List<ChatItemResponse>
)