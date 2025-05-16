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
    entries: Map<LocalDate, Int>,
    onMonthChange: (Int, Int) -> Unit,
    onDayClick: (LocalDate) -> Unit
) {
    RoundedWhiteBox {
        Column {
            MonthCustomCalendarHeader(year, month, onMonthChange)
            Spacer(modifier = Modifier.height(16.dp))
            MonthCustomCalendarGrid(
                year = year,
                month = month,
                entries = entries,
                onDayClick = onDayClick
            )
        }
    }
}
