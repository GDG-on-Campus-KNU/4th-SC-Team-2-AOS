package com.example.soop.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.example.soop.chat.widget.ChatTabsScreen
import com.example.soop.itemlist.BackgroundColorList
import com.example.soop.widget.FragmentTitle

@Composable
fun ChatFragment() {
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
            FragmentTitle(title = "Chat")
            ChatTabsScreen()
        }
    }
}