package com.example.soop.emotionlogs.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.soop.emotionlogs.CalendarMode
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import com.example.soop.R


@Composable
fun CalendarModeToggleRow(currentMode: CalendarMode, onModeChange: (CalendarMode) -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
        CalendarMode.values().forEach { mode ->
            val selected = mode == currentMode
            val interactionSource = remember { MutableInteractionSource() }

            Button(
                onClick = { onModeChange(mode) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selected) Color.Black else Color.White,
                    contentColor = if (selected) Color.White else colorResource(R.color.middle_gray_text)
                ),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .wrapContentSize()
                    .padding(0.dp)
                    .heightIn(min = 0.dp)
                    .defaultMinSize(minHeight = 0.dp),
                interactionSource = interactionSource,
                border = if (selected) null else BorderStroke(width = 1.dp, color = Color(0xFFF4F4F4)),
                contentPadding = PaddingValues(vertical = 0.dp, horizontal = 12.dp)
            ) {
                Text(
                    text = mode.name,
                    fontSize = 14.sp,
                )
            }
        }
    }
}
