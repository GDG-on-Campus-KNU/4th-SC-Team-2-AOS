package com.example.soop.chat.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soop.chat.api.getChatbotList
import com.example.soop.chat.api.postCustomChatbot
import com.example.soop.chat.data.CustomChatbotData
import com.example.soop.chat.request.CustomChatbotRequest
import com.example.soop.chat.viewmodel.ChatbotViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CustomChatbotViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(CustomChatbotRequest())
    val uiState: StateFlow<CustomChatbotRequest> = _uiState

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

    fun onImageChange(new: Int) {
        _uiState.update { it.copy(image = new) }
    }

    fun save(
        onSuccess: () -> Unit = {},
        onError: (String) -> Unit = {}
    ) {
        val current = _uiState.value
        var empathyLevel = ""
        var tone = ""
        when(current.empathyLevel){
            "Cool & Rational"-> empathyLevel = "COOL_RATIONAL"
            "Factual"-> empathyLevel = "FACTUAL"
            "Balanced"-> empathyLevel = "BALANCED"
            "Warm & Understanding"-> empathyLevel = "WARM_UNDERSTANDING"
            "Empathetic & Caring"-> empathyLevel = "EMPATHETIC_CARING"
        }

        when(current.tone){
            "Formal"-> tone = "FORMAL"
            "Direct & Honest"-> tone = "DIRECT_HONEST"
            "Casual & Friendly"-> tone = "CASUAL_FRIENDLY"
            "Witty & Playful"-> tone = "WITTY_PLAYFUL"
            "Calm & Soft"-> tone = "CALM_SOFT"
        }

        postCustomChatbot(
            name = current.name,
            description = current.description,
            empathyLevel = empathyLevel,
            tone = tone,
            image = current.image,
            onSuccess = onSuccess,
            onError = onError
        )
    }

}