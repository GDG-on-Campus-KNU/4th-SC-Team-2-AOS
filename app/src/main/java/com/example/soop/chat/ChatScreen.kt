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
import com.example.soop.itemlist.BackgroundColorList

@Composable
fun ChatScreen() {
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
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)) {
                Text32sp("Chat")
                Image(painter = painterResource(id = R.drawable.add), contentDescription = "add chat button", Modifier.size(40.dp))
            }
            ChatTabsScreen()
        }
    }
}