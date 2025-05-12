package com.example.soop.emotionlogs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.soop.emotionlogs.widget.CalendarModeToggleRow
import com.example.soop.emotionlogs.widget.MonthCustomCalendar.MonthCustomCalendar
import java.time.LocalDate

@Composable
fun EmotionLogsCalendarScreen() {
    var calendarMode by remember { mutableStateOf(CalendarMode.Month) }
    val entries = mapOf(
        LocalDate.of(2025, 3, 1) to listOf(Color.Green, Color.Green),
        LocalDate.of(2025, 3, 3) to listOf(Color.Red, Color.Yellow, Color.Green),
        LocalDate.of(2025, 3, 5) to listOf(Color(0xFFADFF2F), Color(0xFF00FA9A))
    )

    Column {
        CalendarModeToggleRow(
            currentMode = calendarMode,
            onModeChange = { calendarMode = it }
        )
        Spacer(Modifier.padding(10.dp))
        when (calendarMode) {
            CalendarMode.Month -> MonthCustomCalendar(year = 2025, month = 3, entries = entries, onMonthChange = { year, month -> /* TODO: 구현 */ })
            CalendarMode.Week -> MonthCustomCalendar(year = 2025, month = 3, entries = entries, onMonthChange = { year, month -> /* TODO: 구현 */ })
            CalendarMode.Today -> MonthCustomCalendar(year = 2025, month = 3, entries = entries, onMonthChange = { year, month -> /* TODO: 구현 */ })
        }
    }
}

enum class CalendarMode {
    Month, Week, Today
}
