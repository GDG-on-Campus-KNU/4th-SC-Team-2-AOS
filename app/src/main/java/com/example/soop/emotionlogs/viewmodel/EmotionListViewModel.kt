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

    var emotions = mutableStateOf<List<Emotion>>(emptyList())
        private set

    init {
        viewModelScope.launch {
            emotions.value = repo.getAllEmotions()
        }
    }

    fun addEmotion(newName: String) {
        viewModelScope.launch {
            repo.insertEmotion(Emotion(name = newName, imageIdx = emotions.value.size % 7))
            emotions.value = repo.getAllEmotions()
        }
    }
}
