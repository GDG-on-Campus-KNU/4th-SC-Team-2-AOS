package com.example.soop.emotionlogs.viewmodel

import androidx.lifecycle.ViewModel
import com.example.soop.chat.data.CustomChatbotData
import com.example.soop.database.Emotion
import com.example.soop.emotionlogs.data.EmotionLogData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDateTime

class EmotionViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(EmotionLogData())
    val uiState: StateFlow<EmotionLogData> = _uiState

    fun onNameChange(new: String) {
        _uiState.update { it.copy(name = new) }
    }

    fun onGroupChange(new: String) {
        _uiState.update { it.copy(group = new) }
    }

    fun onImageChange(new: Int) {
        _uiState.update { it.copy(image = new) }
    }

    fun onContentChange(new: String) {
        _uiState.update { it.copy(content = new) }
    }

    fun onRecordedAtChange(new: LocalDateTime) {
        _uiState.update { it.copy(recordedAt = new) }
    }

    fun save() {
        val current = _uiState.value
        println("저장: ${current.name}, ${current.group}, ${current.image}, ${current.content}, ${current.recordedAt}")
    }
}