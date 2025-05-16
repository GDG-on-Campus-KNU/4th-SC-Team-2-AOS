package com.example.soop.report.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.util.TableInfo
import com.example.soop.R
import com.example.soop.report.item.NegativeItem

@Composable
fun PositiveFeedback(positive: List<String>) {
    Column {
        Text("Positive Trigger Strengthening Strategies", fontSize = 14.sp)
        positive.forEachIndexed { index, trigger ->
            Spacer(modifier = Modifier.padding(4.dp))
            Text(text = "· $trigger", fontSize = 13.sp, color = colorResource(R.color.middle_gray_text))
        }
    }
}