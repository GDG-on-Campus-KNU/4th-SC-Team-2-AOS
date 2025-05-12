package com.example.soop.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.soop.Text32sp
import com.example.soop.R
import com.example.soop.chat.widget.ChatTabsScreen
import com.example.soop.emotionlogs.EmotionLogsCalendarScreen
import com.example.soop.itemlist.BackgroundColorList
import com.example.soop.widget.FragmentTitle

@Composable
fun EmotionLogsFragment() {
    val backgroundColorList = BackgroundColorList()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = backgroundColorList
                )
            ),
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            FragmentTitle(title = "Emotion Logs")
            Spacer(Modifier.padding(10.dp))
            EmotionLogsCalendarScreen()
        }
    }
}