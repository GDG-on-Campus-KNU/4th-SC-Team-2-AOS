package com.example.soop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.soop.emotionlogs.viewmodel.EmotionListViewModel
import com.example.soop.itemlist.BackgroundColorList
import com.example.soop.ui.theme.SOOPTheme
import com.example.soop.widget.ActivityTitle
import androidx.compose.runtime.*
import androidx.core.app.ActivityCompat
import com.example.soop.database.Emotion
import com.example.soop.emotionlogs.viewmodel.EmotionViewModel
import com.example.soop.emotionlogs.widget.EmotionLogsCarousel
import com.example.soop.emotionlogs.widget.EmotionTextField
import java.time.LocalDateTime
import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.pm.PackageManager
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.example.soop.emotionlogs.api.postEmotionLog

class AddEmotionLogActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 1)
        }

        setContent {
            SOOPTheme {
                AddEmotionLogScreen()
            }
        }
    }
}

@Composable
fun AddEmotionLogScreen() {
    val backgroundColorList = BackgroundColorList()
    val viewModel: EmotionListViewModel = viewModel()
    val emotions by viewModel.emotions
    val emotionsAdd = emotions + Emotion(name = "Your Emotion", imageIdx = 0)
    val emotionViewModel: EmotionViewModel = viewModel()
    val emotionTextField = EmotionTextField()
    val dateTime = LocalDateTime.now()
    val activity = LocalContext.current as? ComponentActivity

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(colors = backgroundColorList)
            )
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 20.dp)
        ) {
            Box(Modifier.padding(horizontal = 20.dp)){
                ActivityTitle(title = "Add Emotion Log", leftIcon = R.drawable.delete, rightIcon = R.drawable.check,
                    onLeftClick = {activity?.finish()},
                    onRightClick = {
                        emotionViewModel.onRecordedAtChange(LocalDateTime.now().toString())
                        postEmotionLog (
                            name = emotionViewModel.uiState.value.emotionName,
                            content = emotionViewModel.uiState.value.content,
                            group = emotionViewModel.uiState.value.emotionGroup,
                            image = emotionViewModel.uiState.value.image,
                            recordedAt = emotionViewModel.uiState.value.recordedAt,
                            onError = {},
                            onSuccess = {
                                Log.d("EmotionLog", "등록 성공")
                                activity?.setResult(RESULT_OK) // <-- 결과 설정
                                activity?.finish()
                            })
                    })
                Spacer(modifier = Modifier.padding(10.dp))
            }

            Column {
                Spacer(Modifier.padding(20.dp))
                EmotionLogsCarousel(
                    items = emotionsAdd,
                    modifier = Modifier.padding(bottom = 20.dp),
                    onLastItemClick = { name ->
                        viewModel.addEmotion(name)
                    },
                    viewModel = emotionViewModel,
                    emotionTextField = emotionTextField
                )
            }

            Column(Modifier.padding(horizontal = 20.dp), verticalArrangement = Arrangement.Bottom) {
                emotionTextField.EmotionRecordedDate(dateTime = dateTime, viewModel = emotionViewModel)
                Spacer(Modifier.padding(5.dp))
                emotionTextField.EmotionRecordedTime(dateTime = dateTime, viewModel = emotionViewModel)
                Spacer(Modifier.padding(5.dp))
                emotionTextField.EmotionContentTextField(
                    viewModel = emotionViewModel
                )
            }
        }
    }
}
