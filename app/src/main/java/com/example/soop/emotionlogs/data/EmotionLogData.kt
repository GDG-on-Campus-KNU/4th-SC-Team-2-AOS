package com.example.soop.emotionlogs.data

import java.time.LocalDateTime

data class EmotionLogData(
    val name: String = "emotion",
    val group: String = "emotionGroup",
    val image: Int = 0,
    val content: String = "content",
    val recordedAt: LocalDateTime = LocalDateTime.now(),
)