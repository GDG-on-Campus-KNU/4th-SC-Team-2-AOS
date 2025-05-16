package com.example.soop.emotionlogs.item

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soop.R
import com.example.soop.itemlist.GradationCircleImage
import com.example.soop.widget.TextOneLine

@Composable
fun EmotionItem(image: Int, emotionName: String, content: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 0.5.dp,
                color = Color(0xFFEFEFEF),
                shape = RoundedCornerShape(50.dp)
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(50.dp)
            )
            .padding(10.dp)
    ) {
        Row {
            GradationCircleImage(image, 52)
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = emotionName, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(2.dp))
                TextOneLine(content, colorResource(R.color.middle_gray_text), 13, Modifier)
            }
        }
    }
}