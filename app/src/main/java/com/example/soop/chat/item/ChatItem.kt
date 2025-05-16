package com.example.soop.chat.ui

import android.R.attr.radius
import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.soop.R
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soop.RoundedWhiteBox
import com.example.soop.chat.response.ChatItemResponse
import com.example.soop.itemlist.GradationCircleImage
import java.nio.file.WatchEvent

@Composable
fun ChatItem(item: ChatItemResponse, image: Int) {
    val bubbleColor = Color.White
    val alignment = if (item.senderId != 0) Alignment.End else Alignment.Start

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = if (item.senderId != 0) Arrangement.End else Arrangement.Start
    ) {
        if (item.senderId == 0) {
            GradationCircleImage(index = image, size = 32)
            Spacer(modifier = Modifier.padding(5.dp))
        } else {
            null
        }
        Box(
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(24.dp))
                .padding(15.dp)
                .widthIn(max = 280.dp)
                .wrapContentSize()
        ) {
            Text(text = item.content, color = Color(0xFF111111), style = MaterialTheme.typography.bodyMedium, fontSize = 15.sp)
        }
    }
}
