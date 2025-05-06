package com.example.soop.chat.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.soop.chat.item.ChatbotListItemData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChatbotViewModel : ViewModel() {
    private val _items = MutableStateFlow<List<ChatbotListItemData>>(emptyList())
    val items: StateFlow<List<ChatbotListItemData>> = _items.asStateFlow()

    init {
        _items.value = listOf(
            ChatbotListItemData(0, "Unconditional empathy", "I give you unconditional empathy."),
            ChatbotListItemData(1, "Professor", "It gives you academic knowledge and advice like a professor."),
            ChatbotListItemData(2, "NurtureBot", "This bot comforts me like a mother's heart."),
            )
        Log.d("ChatBotViewModel", "Initialized with: ${_items.value}")
    }

    fun addItem(item: ChatbotListItemData) {
        val updatedList = _items.value.toMutableList()
        updatedList.add(item)
        _items.value = updatedList
        Log.d("ChatBot", "addItem: $updatedList")
    }
}
