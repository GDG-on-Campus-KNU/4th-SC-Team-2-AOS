package com.example.soop.chat.viewmodel

import androidx.lifecycle.ViewModel
import com.example.soop.chat.data.CustomChatbotData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CustomChatbotViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(CustomChatbotData())

    val uiState: StateFlow<CustomChatbotData> = _uiState

    fun onNameChange(new: String) {
        _uiState.update { it.copy(name = new) }
    }

    fun onDescriptionChange(new: String) {
        _uiState.update { it.copy(description = new) }
    }

    fun onEmpathyLevelChange(new: String) {
        _uiState.update { it.copy(empathyLevel = new) }
    }

    fun onToneChange(new: String) {
        _uiState.update { it.copy(tone = new) }
    }

    fun save() {
        val current = _uiState.value
        // 서버로 전송 등
        println("저장: ${current.name}, ${current.description}, ${current.empathyLevel}, ${current.tone}")
    }
}