package com.example.soop.emotionlogs.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.soop.database.Emotion
import com.example.soop.database.EmotionRepository
import kotlinx.coroutines.launch

class EmotionListViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = EmotionRepository(application)
    // Compose가 구독할 수 있는 State
    var emotions = mutableStateOf<List<Emotion>>(emptyList())
        private set

    init {
        viewModelScope.launch {
            // DB에서 불러와서 State 업데이트
            emotions.value = repo.getAllEmotions()
        }
    }

    fun addEmotion(newName: String) {
        viewModelScope.launch {
            repo.insertEmotion(Emotion(name = newName, imageIdx = 0))
            // 삽입 후 다시 리스트 갱신
            emotions.value = repo.getAllEmotions()
        }
    }
}
