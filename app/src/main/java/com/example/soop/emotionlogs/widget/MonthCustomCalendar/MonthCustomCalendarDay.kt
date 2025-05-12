package com.example.soop.emotionlogs.widget.MonthCustomCalendar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soop.R

@Composable
fun MonthCustomCalendarDay(day: Int, colors: List<Color>?) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(
                brush = if (!colors.isNullOrEmpty()) {
                    Brush.linearGradient(colors)
                } else {
                    Brush.linearGradient(listOf(Color.White, Color.White))
                }
            )
            .border(border = BorderStroke(width = 1.dp, color = Color(0xFFEBEBEB)), shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$day",
            fontSize = 14.sp,
            color = colorResource(R.color.middle_gray_text),
            onTextLayout = {}
        )
    }
}
