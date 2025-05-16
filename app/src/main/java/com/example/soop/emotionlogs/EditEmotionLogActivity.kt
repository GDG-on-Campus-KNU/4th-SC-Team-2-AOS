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
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

class EditEmotionLogActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 1)
        }

        setContent {
            SOOPTheme {
                EditEmotionLogScreen()
            }
        }
    }
}

@Composable
fun EditEmotionLogScreen() {
    val backgroundColorList = BackgroundColorList()
    val viewModel: EmotionListViewModel = viewModel()
    val emotions by viewModel.emotions
    val emotionsAdd = emotions + Emotion(name = "Your Emotion", imageIdx = 0)
    val emotionViewModel: EmotionViewModel = viewModel()
    val emotionTextField = EmotionTextField()
    val dateTime = LocalDateTime.now()

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
                ActivityTitle(title = "Add Emotion Log", leftIcon = R.drawable.delete, rightIcon = R.drawable.check)
                Spacer(modifier = Modifier.padding(10.dp))
            }

        }
    }
}
