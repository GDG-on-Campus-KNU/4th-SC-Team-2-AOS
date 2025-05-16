package com.example.soop.emotionlogs.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soop.RoundedWhiteBox
import com.example.soop.emotionlogs.response.DailyEmotionResponse
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@Composable
fun DailyEmotionBox(dailyList: List<DailyEmotionResponse>) {
    if (dailyList.isEmpty()) return

    RoundedWhiteBox {
        Column {
            val firstEmotion = dailyList.firstOrNull()?.emotions?.firstOrNull()
            val recordedDay = try {
                firstEmotion?.let {
                    val parsedDate = LocalDateTime.parse(it.recordedAt, DateTimeFormatter.ISO_DATE_TIME)
                    parsedDate.toLocalDate().dayOfMonth.toString()
                } ?: ""
            } catch (e: DateTimeParseException) {
                firstEmotion?.recordedAt ?: ""
            }
            Text(text = "${dailyList.firstOrNull()?.dayOfWeek ?: ""} $recordedDay", fontSize = 16.sp)
            Spacer(modifier = Modifier.padding(top = 5.dp))
            dailyList.forEach { response ->
                response.emotions.forEach { emotion ->
                    Spacer(modifier = Modifier.padding(top = 5.dp))
                    EmotionItem(
                        image = emotion.image,
                        emotionName = emotion.emotionName,
                        content = emotion.content
                    )
                }
            }
        }
    }
}

