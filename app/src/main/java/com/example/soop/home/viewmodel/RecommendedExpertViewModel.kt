package com.example.soop.chat.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.soop.chat.item.ChatbotListItemData
import com.example.soop.home.data.RecommendedExpertItemData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RecommendedExpertViewModel : ViewModel() {
    private val _items = MutableStateFlow<List<RecommendedExpertItemData>>(emptyList())
    val items: StateFlow<List<RecommendedExpertItemData>> = _items.asStateFlow()

    init {
        _items.value = listOf(
            RecommendedExpertItemData(0, "Dr.Olivia Hart", "Mental Health Medicine"),
            RecommendedExpertItemData(1, "Mental Health Welfare Centers", "Clinical Psychology"),
            RecommendedExpertItemData(1, "Mental Health Welfare Centers", "Clinical Psychology"),
        )
        Log.d("ExpertViewModel", "Initialized with: ${_items.value}")
    }

    fun addItem(item: RecommendedExpertItemData) {
        val updatedList = _items.value.toMutableList()
        updatedList.add(item)
        _items.value = updatedList
        Log.d("Expert", "addItem: $updatedList")
    }
}
