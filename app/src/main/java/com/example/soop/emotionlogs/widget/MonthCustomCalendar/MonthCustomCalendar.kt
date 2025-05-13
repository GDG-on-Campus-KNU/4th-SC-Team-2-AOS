package com.example.soop.emotionlogs.widget.MonthCustomCalendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.soop.RoundedWhiteBox
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
fun MonthCustomCalendar(
    year: Int,
    month: Int,
    entries: Map<LocalDateTime, List<Color>>, // 날짜별 기록 색상 (여러 개 가능)
    onMonthChange: (Int, Int) -> Unit
) {
    RoundedWhiteBox {
        Column {
            MonthCustomCalendarHeader(year, month, onMonthChange)
            Spacer(modifier = Modifier.height(16.dp))
            MonthCustomCalendarGrid(year, month, entries)
        }
    }

}
