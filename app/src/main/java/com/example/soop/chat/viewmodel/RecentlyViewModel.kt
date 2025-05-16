package com.example.soop.chat.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soop.chat.api.getChatbotList
import com.example.soop.chat.api.getRecentlyList
import com.example.soop.chat.response.ChatbotListResponse
import com.example.soop.chat.response.RecentlyListResponse
import com.example.soop.chat.viewmodel.ChatbotViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecentlyViewModel : ViewModel() {
    private val _items = MutableStateFlow<List<RecentlyListResponse>>(emptyList())
    val items: StateFlow<List<RecentlyListResponse>> = _items.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private var isInitialized = false

    fun init(onError: (String) -> Unit) {
        if (isInitialized) return
        isInitialized = true
        fetchRecentlyList(onError)
    }

    fun refresh(onError: (String) -> Unit) {
        fetchRecentlyList(onError)
    }

    private fun fetchRecentlyList(onError: (String) -> Unit) {
        _isLoading.value = true
        viewModelScope.launch {
            getRecentlyList(
                viewModel = this@RecentlyViewModel,
                onError = { err ->
                    Log.e("RecentlyViewModel", "Error loading Recently list: $err")
                    onError(err)
                    _isLoading.value = false
                },
                onSuccess = {
                    _isLoading.value = false
                }
            )
        }
    }

    fun onRecentlyListChange(list: List<RecentlyListResponse>) {
        _items.value = list
        Log.d("Recently", "Updated recently list: $list")
    }
}
