package com.example.soop.report.response

data class FeedbackResponse(
    val positiveTriggers: List<Trigger> = listOf(Trigger()),
    val negativeTriggers: List<Trigger> = listOf(Trigger()),
    val positiveStrategies: List<String> = listOf(""),
    val negativeStrategies: List<String> = listOf("")
)

data class Trigger(
    val trigger: String = "",
    val count: Int = 0
)
