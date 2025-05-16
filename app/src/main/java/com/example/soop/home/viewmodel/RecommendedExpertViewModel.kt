package com.example.soop.chat.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.soop.chat.item.ChatbotListItemData
import com.example.soop.chat.response.ChatbotListResponse
import com.example.soop.home.response.ExpertResponseWrapper
import com.example.soop.home.response.MentalTipResponseWrapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RecommendedExpertViewModel : ViewModel() {
    private val _items = MutableStateFlow(ExpertResponseWrapper(data = emptyList()))
    val items: MutableStateFlow<ExpertResponseWrapper> = _items

    fun onExpertListChange(new: ExpertResponseWrapper) {
        _items.value = new
    }
}
