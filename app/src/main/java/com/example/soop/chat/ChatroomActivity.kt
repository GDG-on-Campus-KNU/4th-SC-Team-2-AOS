package com.example.soop.chat

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import com.example.soop.itemlist.BackgroundColorList
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.soop.R
import com.example.soop.chat.ui.ChatItem
import com.example.soop.chat.viewmodel.ChatroomViewModel
import com.example.soop.chat.widget.*
import com.example.soop.itemlist.BackgroundColorList
import com.example.soop.ui.theme.SOOPTheme
import com.example.soop.widget.ActivityTitle
import com.example.soop.widget.Text20sp
import com.example.soop.widget.UpRoundedWhiteBox
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.soop.STTManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ChatroomActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val nameOfChatbot = intent.getStringExtra("nameOfChatbot")
        val imageOfChatbot = intent.getIntExtra("imageOfChatbot", 0)
        val idOfChatroom = intent.getIntExtra("idOfChatroom", 0)
        val jwtToken = com.example.soop.SoopApp.securePrefs.getString("access_token", null)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 1)
        }

        setContent {
            SOOPTheme {
                ChatroomScreen(nameOfChatbot, imageOfChatbot, idOfChatroom, jwtToken)
            }
        }
    }
}

@Composable
fun ChatroomScreen(nameOfChatbot: String?, imageOfChatbot: Int, idOfChatroom: Int, jwtToken: String?) {
    val viewModel: ChatroomViewModel = viewModel()
    val backgroundColorList = BackgroundColorList()
    val chatList by viewModel.chatList.collectAsState()
    val name = nameOfChatbot ?: "Chatbot"
    var input by remember { mutableStateOf("") }
    val ip = stringResource(id = R.string.server)
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val activity = LocalContext.current as? ComponentActivity

    var isRecording by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    val sttManager = remember { STTManager(context) }
    LaunchedEffect(Unit) {
        sttManager.initialize()
    }

    fun startSpeechToText(onResult: (String) -> Unit) {
        sttManager.startListening(
            onResult = { recognizedText ->
                onResult(recognizedText)
            },
            onError = { errorMessage ->
                // 필요 시 에러 처리
            }
        )
    }

    // 🟢 WebSocket 연결 (한 번만)
    LaunchedEffect(Unit) {
        if (!jwtToken.isNullOrBlank()) {
            viewModel.onChangeChatroomId(idOfChatroom)
            viewModel.loadInitialMessages(idOfChatroom)
            viewModel.connectWebSocket(jwtToken, ip)
        }
        else {
            Log.e("SOCKET", "토큰이 없어 연결을 스킵했습니다")
        }
    }

    LaunchedEffect(chatList.size) {
        coroutineScope.launch {
            listState.animateScrollToItem(index = 0)
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(brush = Brush.verticalGradient(colors = backgroundColorList))) {
        Box(modifier = Modifier.padding(20.dp)){
            ActivityTitle(title = name, rightIcon = R.drawable.delete, onRightClick = {activity?.finish()})
        }
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 10.dp, start = 20.dp, end = 20.dp),
            reverseLayout = true,
            state = listState
        ) {
            items(items = chatList.reversed(), key = { it.createdAt }) { item ->
                ChatItem(item, imageOfChatbot)
            }
        }
        UpRoundedWhiteBox(modifier = Modifier.imePadding()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                BasicTextField(
                    value = input,
                    onValueChange = { input = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    singleLine = true,
                    textStyle = TextStyle(fontSize = 15.sp, color = Color.Black),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Send),
                    keyboardActions = KeyboardActions(
                        onSend = {
                            if (input.isNotBlank()) {
                                viewModel.sendMessage(input)
                                input = ""
                            }
                        }
                    ),
                    decorationBox = { innerTextField ->
                        if (input.isBlank()) {
                            Text("Tell me anything!", color = colorResource(R.color.middle_gray_text))
                        }
                        innerTextField()
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Image(
                    modifier = Modifier
                        .align(Alignment.End)
                        .size(40.dp)
                        .clickable {
                            if (input.isNotBlank()) {
                                viewModel.sendMessage(input)
                                input = ""
                            }
                            else{
                                isRecording = !isRecording
                                if (isRecording) {
                                    startSpeechToText { resultText ->
                                        input += if (input.isBlank()) resultText else "\n$resultText"
                                        isRecording = false
                                    }
                                }
                            }
                        },
                    painter = if (input.isNotBlank() && !isRecording) {
                        painterResource(R.drawable.send)
                    }else if(isRecording){
                        painterResource(R.drawable.record_stop)
                    }else {
                        painterResource(R.drawable.mike_circle)
                    },
                    contentDescription = "버튼",
                )
            }
        }
    }
}



