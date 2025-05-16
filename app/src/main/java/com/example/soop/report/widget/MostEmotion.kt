package com.example.soop.report.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soop.R
import com.example.soop.RoundedWhiteBox

@Composable
fun MostEmotion(emotion: String, percent: Int){
    RoundedWhiteBox {
        Column {
            Text(text = "Most Frequent", fontSize = 16.sp, color = colorResource(R.color.middle_gray_text))
            Spacer(Modifier.padding(10.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom, modifier = Modifier.fillMaxWidth()) {
                Text(text = emotion, fontSize = 32.sp, color = Color(0xFF1CE0A9))
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(text = percent.toString(), fontSize = 32.sp, color = colorResource(R.color.black))
                    Text(text = " %", fontSize = 13.sp, color = colorResource(R.color.middle_gray_text))
                }
            }
        }
    }
}