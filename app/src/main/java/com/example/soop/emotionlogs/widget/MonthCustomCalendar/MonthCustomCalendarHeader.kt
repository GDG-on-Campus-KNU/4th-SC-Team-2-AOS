package com.example.soop.emotionlogs.widget.MonthCustomCalendar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import java.time.format.TextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soop.R
import java.time.Month
import java.util.Locale

@Composable
fun MonthCustomCalendarHeader(year: Int, month: Int, onMonthChange: (Int, Int) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${Month.of(month).getDisplayName(TextStyle.FULL, Locale.ENGLISH)} $year",
            fontSize = 16.sp,
            fontWeight = FontWeight.W500
        )
        Row {
            Image(painter = painterResource(id = R.drawable.back_circle_arrow),
                contentDescription = "Previous Month",
                modifier = Modifier.clickable {
                    onMonthChange(year, month - 1)
                }
                    .size(24.dp))
            Spacer(Modifier.padding(3.dp))
            Image(painter = painterResource(id = R.drawable.next_circle_arrow),
                contentDescription = "Next Month",
                modifier = Modifier.clickable {
                    onMonthChange(year, month + 1)
                }
                    .size(24.dp))
        }
    }
}
