package com.example.soop.home.response

data class PositivitySummary(
    val code: String = "",
    val data: PositivityData = PositivityData(),
    val message: String = "",
    val success: Boolean = false
)

data class PositivityData(
    val increaseRate: Int = 0,
    val blocks: List<PositivityBlock> = emptyList()
)

data class PositivityBlock(
    val day: String = "",
    val count: Int = 0,
    val blocks: Int = 0
)
