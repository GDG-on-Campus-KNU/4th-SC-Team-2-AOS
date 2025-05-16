package com.example.soop.home.viewmodel

import androidx.lifecycle.ViewModel
import com.example.soop.chat.data.CustomChatbotData
import com.example.soop.home.data.HomeData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(HomeData())

    val uiState: StateFlow<HomeData> = _uiState

    fun onNameChange(new: String) {
        _uiState.update { it.copy(nickName = new) }
    }
}