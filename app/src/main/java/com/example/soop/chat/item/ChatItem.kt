package com.example.soop.chat.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import com.example.soop.R
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.soop.chat.data.ChatItemData

@Composable
fun ChatItem(item: ChatItemData) {
    val bubbleColor = Color.White
    val alignment = if (item.senderId == 0) Alignment.End else Alignment.Start
    val image = if (item.senderId != 0) {
        Image(
            painter = painterResource(id = R.drawable.gradation_circle_blue),
            contentDescription = null,
            modifier = Modifier.size(32.dp)
        )
    } else {
        null
    }


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 4.dp),
        horizontalArrangement = if (item.senderId == 0) Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .background(bubbleColor)
                .padding(12.dp)
                .widthIn(max = 260.dp)
        ) {
            Text(text = item.content, color = Color.Black, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
