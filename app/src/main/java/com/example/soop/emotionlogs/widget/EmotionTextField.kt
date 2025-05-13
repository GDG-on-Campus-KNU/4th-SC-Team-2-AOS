package com.example.soop.emotionlogs.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text // material 패키지의 Text 사용
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.soop.R
import com.example.soop.RoundedWhiteBox
import com.example.soop.STTManager
import com.example.soop.emotionlogs.viewmodel.EmotionViewModel
import java.time.LocalDateTime
import java.time.Month
import java.time.format.DateTimeFormatter
import java.util.Locale

class EmotionTextField {
    @Composable
    fun AddEmotionTextField(
        value: String,
        onValueChange: (String) -> Unit,
        modifier: Modifier = Modifier,
        placeholderText: String = "Your Emotion",
        viewModel: EmotionViewModel
    ) {

        BasicTextField(
            value = value,
            onValueChange = {
                onValueChange(it)
                viewModel.onNameChange(it)
            },
            modifier = modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(24.dp)
                )
                .padding(horizontal = 8.dp, vertical = 7.dp)
                .heightIn(min = 0.dp)
                .defaultMinSize(minHeight = 0.dp)
                .fillMaxWidth(),
            textStyle = LocalTextStyle.current.copy(
                fontSize = 14.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            ),
            visualTransformation = VisualTransformation.None,
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholderText,
                            color = colorResource(id = R.color.middle_gray_text),
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth() // 필요에 따라 Modifier 조정
                        )
                    }
                    innerTextField()
                }
            }
        )
    }

    @Composable
    fun EmotionGroupSelector(
        viewModel: EmotionViewModel
    ) {
        var expanded by remember { mutableStateOf(false) }
        val options = listOf("POSITIVE", "NEGATIVE", "NEUTRAL")
        var selectedGroup by remember { mutableStateOf<String?>(null) }

        val isSelected = selectedGroup != null

        Box(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .clickable { expanded = true }
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(50)
                    )
                    .padding(horizontal = 12.dp, vertical = 7.dp)
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .align(Alignment.Center)
            ) {
                androidx.compose.material3.Text(
                    text = selectedGroup ?: "Select Group",
                    color = if (isSelected) Color.Black else colorResource(id = R.color.middle_gray_text),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(Color.White)
            ) {
                options.forEach { group ->
                    DropdownMenuItem(
                        text = { androidx.compose.material3.Text(text = group, fontSize = 14.sp) },
                        onClick = {
                            selectedGroup = group
                            expanded = false
                            viewModel.onGroupChange(group)
                        }
                    )
                }
            }
        }
    }

    @Composable
    fun EmotionRecordedDate(dateTime: LocalDateTime){
        val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy, E", Locale.ENGLISH)
        val formatted = dateTime.format(formatter)

        RoundedWhiteBox(radius = 12) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Date",
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Text(
                    text = formatted,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    color = colorResource(id = R.color.middle_gray_text)
                )
            }
        }
    }

    @Composable
    fun EmotionRecordedTime(dateTime: LocalDateTime, viewModel: EmotionViewModel){
        val formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.ENGLISH)
        val formatted = dateTime.format(formatter)

        RoundedWhiteBox(radius = 12) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Time",
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Text(
                    text = formatted,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    color = colorResource(id = R.color.middle_gray_text)
                )
            }
            viewModel.onRecordedAtChange(dateTime)
        }
    }

    @Composable
    fun EmotionContentTextField(
        modifier: Modifier = Modifier,
        placeholderText: String = "Tell me anything!",
        viewModel: EmotionViewModel
    ) {
        var textState by rememberSaveable { mutableStateOf("") }
        var isRecording by rememberSaveable { mutableStateOf(false) }
        val context = LocalContext.current
        val sttManager = remember { STTManager(context) }

        sttManager.initialize() // STTManager 초기화

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

        RoundedWhiteBox {
            Box(modifier = Modifier.fillMaxSize()) {
                BasicTextField(
                    value = textState,
                    onValueChange = {
                        textState = it
                        viewModel.onContentChange(it)
                    },
                    modifier = modifier
                        .fillMaxSize(),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 15.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    ),
                    visualTransformation = VisualTransformation.None,
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.TopStart
                        ) {
                            if (textState.isEmpty()) {
                                Text(
                                    text = placeholderText,
                                    color = colorResource(id = R.color.middle_gray_text),
                                    fontSize = 15.sp,
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                            innerTextField()
                        }
                    }
                )

                Image(
                    painter = painterResource(
                        if (isRecording) R.drawable.record_stop else R.drawable.mike_circle
                    ),
                    contentDescription = "stt icon",
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.BottomEnd)
                        .clickable {
                            isRecording = !isRecording
                            if (isRecording) {
                                startSpeechToText { resultText ->
                                    textState += if (textState.isBlank()) resultText else "\n$resultText"
                                    viewModel.onContentChange(textState)
                                    isRecording = false
                                }
                            }
                        }
                )
            }
        }
    }
}