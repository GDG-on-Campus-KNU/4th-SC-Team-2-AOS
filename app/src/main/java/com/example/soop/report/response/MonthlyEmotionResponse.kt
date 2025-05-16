package com.example.soop.report.response

data class MonthlyEmotionReportResponse(
    val totalLogs: Int = 0,
    val emotionGroupCounts: EmotionGroupCounts = EmotionGroupCounts(0,0,0),
    val mostFrequentEmotion: String= "",
    val mostFrequentPercentage: Int=0,
    val leastFrequentEmotion: String="",
    val leastFrequentPercentage: Int=0
)

data class EmotionGroupCounts(
    val additionalProp1: Int=0,
    val additionalProp2: Int=0,
    val additionalProp3: Int=0
)