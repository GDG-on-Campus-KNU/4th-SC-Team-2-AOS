package com.example.soop.report.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soop.R

@Composable
fun NegativeFeedback(negative: List<String>) {
    Column {
        Text("Negative Trigger Mitigation Strategies", fontSize = 14.sp)
        negative.forEachIndexed { index, trigger ->
            Spacer(modifier = Modifier.padding(4.dp))
            Text(text = "· $trigger", fontSize = 13.sp, color = colorResource(R.color.middle_gray_text))
        }
    }
}