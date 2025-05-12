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
import com.example.soop.emotionlogs.widget.EmotionLogsCarousel

class AddEmotionLogActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
    val emotions1 = listOf("sadness","anxiety","joy","gratitude","relief","boredom")

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

            Column(Modifier.fillMaxSize()) {
                /*emotions.forEach { emotion ->
                    Text(text = emotion.name)
                }*/
                EmotionLogsCarousel(items = emotions1, modifier = Modifier.padding(bottom = 20.dp))

                Button(onClick = { viewModel.addEmotion("New Emotion") }) {
                    Text("Add Emotion")
                }
            }
        }
    }
}
