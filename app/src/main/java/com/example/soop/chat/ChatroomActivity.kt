package com.example.soop.chat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.soop.R
import com.example.soop.chat.ui.ChatItem
import com.example.soop.chat.viewmodel.ChatroomViewModel
import com.example.soop.chat.viewmodel.CustomChatbotViewModel
import com.example.soop.chat.widget.*
import com.example.soop.itemlist.BackgroundColorList
import com.example.soop.ui.theme.SOOPTheme
import com.example.soop.widget.ActivityTitle
import com.example.soop.widget.Text20sp

class ChatroomActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SOOPTheme {
                ChatroomScreen()
            }
        }
    }
}

@Composable
fun ChatroomScreen(viewModel: ChatroomViewModel = viewModel()) {
    val chatList = viewModel.chatList
    var input by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        ActivityTitle(title = "Create Custom", leftIcon = R.drawable.back_arrow)
        Spacer(modifier = Modifier.padding(10.dp))
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 8.dp),
            reverseLayout = true
        ) {
            items(items = chatList.reversed(), key = { it.chatId }) { item ->
                ChatItem(item)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            TextField(
                value = input,
                onValueChange = { input = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("메시지를 입력하세요") },
                singleLine = true
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                if (input.isNotBlank()) {
                    viewModel.sendUserMessage(input)
                    input = ""
                }
            }) {
                Text("보내기")
            }
        }
    }
}


