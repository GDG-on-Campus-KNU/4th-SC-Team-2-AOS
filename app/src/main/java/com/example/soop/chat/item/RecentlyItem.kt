package com.example.soop.chat.widget

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.soop.*
import com.example.soop.R
import com.example.soop.chat.response.RecentlyListResponse
import com.example.soop.itemlist.GradationCircleImage
import com.example.soop.widget.TextOneLine
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun RecentlyItem(recentlyListResponse: RecentlyListResponse, modifier: Modifier = Modifier) {
    val imageIndex = recentlyListResponse.image
    val name = recentlyListResponse.botName
    val latestMessage = recentlyListResponse.latestMessage

    val latestTime = LocalDateTime.parse(recentlyListResponse.messageUpdatedAt, DateTimeFormatter.ISO_DATE_TIME)
    val dateTime = LocalDateTime.now()
    val duration = Duration.between(latestTime, dateTime)

    val formatter = DateTimeFormatter.ofPattern("MM/d", Locale.ENGLISH)
    val formatted =  when {
        duration.toDays() == 0L -> "Today"
        duration.toDays() < 7L -> "${duration.toDays()}day"
        else -> latestTime.format(formatter)
    }

    RoundedWhiteBox(modifier = modifier) {
        Row() {
            GradationCircleImage(imageIndex, 44)
            Spacer(modifier = Modifier.padding(5.dp))
            Column() {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextOneLine(text = name, color = Color.Black, fontSize = 15, modifier = Modifier.weight(1f))
                    Spacer(modifier = Modifier.width(1.dp)) // 점과 시간 사이 여백
                    Text12sp(text = "· $formatted")
                }

                Spacer(modifier = Modifier.padding(1.dp))
                TextOneLine(text = latestMessage, color = Color(R.color.middle_gray_text), fontSize = 14)
            }
        }
    }
}