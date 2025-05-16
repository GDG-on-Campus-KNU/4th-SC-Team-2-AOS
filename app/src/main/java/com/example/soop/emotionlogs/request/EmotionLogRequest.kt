package com.example.soop.emotionlogs.request

import java.time.LocalDateTime

data class EmotionLogRequest(
    val emotionName: String = "emotion",
    val emotionGroup: String = "emotionGroup",
    val content: String = "content",
    val recordedAt: String = "",
    val image: Int = 0
)