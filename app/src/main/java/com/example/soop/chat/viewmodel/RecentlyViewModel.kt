package com.example.soop.chat.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.soop.chat.item.RecentlyListItemData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RecentlyViewModel : ViewModel() {
    private val _items = MutableStateFlow<List<RecentlyListItemData>>(emptyList())
    val items: StateFlow<List<RecentlyListItemData>> = _items.asStateFlow()

    init {
        _items.value = listOf(
            RecentlyListItemData(0, "Work-Life Balance", "I wish I could believe that, but sometimes it just feels like I’ll never be enough.", "Today"),
            RecentlyListItemData(1, "Coping with Sadness", "That must have been really hard to deal with.", "1day"),
            RecentlyListItemData(2, "Overcoming Failure", "I can see why that felt so overwhelming.", "4day"),
            RecentlyListItemData(3, "Uncertainty About the Future and Finding Direction", "No judgment, just support. Want to take a deep breath with me?", "03/22"),
            RecentlyListItemData(4, "Dealing with Loneliness", "Alright. Inhale, hold, and exhale. You’re doing great, Sienna.", "03/20"),
            RecentlyListItemData(5, "Stressful Works", "Thanks for saying that. I guess I just needed to let it out.", "03/17"),
            RecentlyListItemData(6, "Stressful Works", "Thanks for saying that. I guess I just needed to let it out.", "03/17"),
            )
        Log.d("ChatBotViewModel", "Initialized with: ${_items.value}")
    }

    fun addItem(item: RecentlyListItemData) {
        val updatedList = _items.value.toMutableList()
        updatedList.add(item)
        _items.value = updatedList
        Log.d("ChatBot", "addItem: $updatedList")
    }
}
