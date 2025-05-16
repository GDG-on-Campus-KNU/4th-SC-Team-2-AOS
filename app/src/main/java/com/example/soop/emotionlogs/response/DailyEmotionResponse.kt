package com.example.soop.emotionlogs.response

data class DailyEmotionResponse(
    val dayOfWeek: String = "MONDAY",
    val emotions: List<DailyEmotion> = listOf(DailyEmotion())
)

data class DailyEmotion(
    val emotionName: String = "string",
    val emotionGroup: String = "POSITIVE",
    val content: String = "string",
    val recordedAt: String = "2025-05-16T14:08:06.215Z",
    val image: Int = 0
)