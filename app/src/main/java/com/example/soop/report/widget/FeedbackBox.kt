package com.example.soop.report.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soop.RoundedWhiteBox

@Composable
fun FeedbackBox(positive: List<String>, negative: List<String>) {
    RoundedWhiteBox {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Feedback", fontSize = 18.sp)
            Spacer(modifier = Modifier.padding(6.dp))
            PositiveFeedback(positive)
            Spacer(modifier = Modifier.padding(5.dp))
            NegativeFeedback(negative)
        }
    }
}