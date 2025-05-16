package com.example.soop.chat.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soop.chat.api.getChatbotList
import com.example.soop.chat.response.ChatbotListResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChatbotViewModel : ViewModel() {

    private val _items = MutableStateFlow<List<ChatbotListResponse>>(emptyList())
    val items: StateFlow<List<ChatbotListResponse>> = _items.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private var isInitialized = false

    fun init(onError: (String) -> Unit) {
        if (isInitialized) return
        isInitialized = true
        fetchChatbotList(onError)
    }

    fun refresh(onError: (String) -> Unit) {
        fetchChatbotList(onError)
    }

    private fun fetchChatbotList(onError: (String) -> Unit) {
        _isLoading.value = true
        viewModelScope.launch {
            getChatbotList(
                viewModel = this@ChatbotViewModel,
                onError = { err ->
                    Log.e("ChatbotViewModel", "Error loading chatbot list: $err")
                    onError(err)
                    _isLoading.value = false
                },
                onSuccess = {
                    _isLoading.value = false
                }
            )
        }
    }

    fun onChatbotListChange(list: List<ChatbotListResponse>) {
        _items.value = list
        Log.d("ChatBot", "Updated chatbot list: $list")
    }
}
