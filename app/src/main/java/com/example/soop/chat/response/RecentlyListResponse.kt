package com.example.soop.chat.response

import java.time.LocalDateTime

data class RecentlyListResponse (
    val roomId: Int = 0,
    val botName: String = "",
    val description: String = "",
    val empathyLevel: String = "",
    val tone: String = "",
    val latestMessage: String = "",
    val messageUpdatedAt: String = "",
    val roomStatus: String = "",
    val image: Int = 0
)

data class RecentlyListWrapper(
    val chatRooms: List<RecentlyListResponse>
)