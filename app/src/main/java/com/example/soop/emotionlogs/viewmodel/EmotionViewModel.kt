package com.example.soop.emotionlogs.viewmodel

import androidx.lifecycle.ViewModel
import com.example.soop.emotionlogs.request.EmotionLogRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDateTime

class EmotionViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(EmotionLogRequest())
    val uiState: StateFlow<EmotionLogRequest> = _uiState

    fun onNameChange(new: String) {
        _uiState.update { it.copy(emotionName = new) }
    }

    fun onGroupChange(new: String) {
        _uiState.update { it.copy(emotionGroup = new) }
    }

    fun onImageChange(new: Int) {
        _uiState.update { it.copy(image = new) }
    }

    fun onContentChange(new: String) {
        _uiState.update { it.copy(content = new) }
    }

    fun onRecordedAtChange(new: String) {
        _uiState.update { it.copy(recordedAt = new) }
    }
}