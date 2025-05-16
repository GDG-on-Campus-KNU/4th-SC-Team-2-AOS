package com.example.soop.emotionlogs

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.soop.emotionlogs.api.getDailyList
import com.example.soop.emotionlogs.item.DailyEmotionBox
import com.example.soop.emotionlogs.viewmodel.CalendarEmotionViewModel
import com.example.soop.emotionlogs.widget.CalendarModeToggleRow
import com.example.soop.emotionlogs.widget.MonthCustomCalendar.MonthCustomCalendar
import java.time.LocalDate

@Composable
fun EmotionLogsCalendarScreen(
    viewModel: CalendarEmotionViewModel = viewModel()
) {
    var calendarMode by remember { mutableStateOf(CalendarMode.Month) }
    val monthlyListState by viewModel.monthlyList.collectAsState()
    val dailyList by viewModel.dailyList.collectAsState()

    val lifecycleOwner = LocalLifecycleOwner.current
    val currentOnError = rememberUpdatedState<(String) -> Unit> { }

    var lastSelectedDate by remember { mutableStateOf<LocalDate?>(null) }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.refresh(onError = currentOnError.value)
                lastSelectedDate?.let { date ->
                    viewModel.fetchDailyList(date.toString()) { error ->
                        Log.e("CalendarScreen", "Daily fetch failed: $error")
                    }
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    val entries: Map<LocalDate, Int> = monthlyListState.mapNotNull { e ->
        runCatching {
            val (y, m, d) = e.date.split("-").map { it.toInt() }
            LocalDate.of(y, m, d)
        }.getOrNull()?.let { date -> date to e.image }
    }.toMap()

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(bottom = 16.dp)
    ) {
        Spacer(Modifier.padding(10.dp))

        MonthCustomCalendar(
            year = 2025,
            month = 5,
            entries = entries,
            onMonthChange = { _, _ -> },
            onDayClick = { date ->
                val formatted = date.toString() // e.g. "2025-05-16"
                lastSelectedDate = date // 날짜 저장
                viewModel.fetchDailyList(formatted) { error ->
                    Log.e("CalendarScreen", "Error: $error")
                }
            }
        )

        Spacer(modifier = Modifier.padding(8.dp))
        DailyEmotionBox(dailyList = dailyList)
    }
}

enum class CalendarMode {
    Month, Week, Today
}
