package com.example.soop.emotionlogs.widget.MonthCustomCalendar

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.soop.emotionlogs.viewmodel.CalendarEmotionViewModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.YearMonth

@Composable
fun MonthCustomCalendarGrid(
    year: Int,
    month: Int,
    entries: Map<LocalDate, Int>,
    onDayClick: (LocalDate) -> Unit
) {
    val daysInMonth = YearMonth.of(year, month).lengthOfMonth()
    val weeks = (1..daysInMonth).chunked(7)

    Column {
        weeks.forEach { week ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 3.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                for (day in week) {
                    val date = LocalDate.of(year, month, day)  // LocalDate 로 생성
                    val imageIndex = entries[date]             // 여기서 매칭

                    Log.d("CalendarDayCheck", "Checking date: $date, imageIndex: $imageIndex")

                    MonthCustomCalendarDay(day = day, imageIndex = imageIndex, onClick = { onDayClick(date) })
                }

                repeat(7 - week.size) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color.Transparent)
                    )
                }
            }
        }
    }
}