package com.example.soop.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.soop.Text32sp
import com.example.soop.R

@Composable
fun ChatScreen() {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFE0FFFE), Color(0xFFF3FCFF), Color(0xFFECFAEF))
                )
            ),
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .verticalScroll(scrollState)
        ) {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Text32sp("Chat")
                Image(painter = painterResource(id = R.drawable.add), contentDescription = "add chat button")
            }
        }
    }
}