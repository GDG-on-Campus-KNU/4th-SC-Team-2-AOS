package com.example.soop.emotionlogs.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soop.chat.api.getRecentlyList
import com.example.soop.chat.response.RecentlyListResponse
import com.example.soop.chat.viewmodel.RecentlyViewModel
import com.example.soop.emotionlogs.api.getDailyList
import com.example.soop.emotionlogs.api.getMonthlyList
import com.example.soop.emotionlogs.request.EmotionLogRequest
import com.example.soop.emotionlogs.response.DailyEmotionResponse
import com.example.soop.emotionlogs.response.MonthlyEmotion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CalendarEmotionViewModel: ViewModel() {
    private val _monthlyList = MutableStateFlow<List<MonthlyEmotion>>(emptyList())
    val monthlyList: StateFlow<List<MonthlyEmotion>> = _monthlyList

    private val _dailyList = MutableStateFlow<List<DailyEmotionResponse>>(emptyList())
    val dailyList: StateFlow<List<DailyEmotionResponse>> = _dailyList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private var isInitialized = false

    val now = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-M")
    val yearMonth = now.format(formatter)

    fun init(onError: (String) -> Unit) {
        if (isInitialized) return
        isInitialized = true
        fetchMonthlyList(onError)
    }

    fun refresh(onError: (String) -> Unit) {
        fetchMonthlyList(onError)
    }

    fun refreshDaily(daily: String, onError: (String) -> Unit) {
        fetchDailyList(daily, onError)
    }

    private fun fetchMonthlyList(onError: (String) -> Unit) {
        _isLoading.value = true
        viewModelScope.launch {
            getMonthlyList(
                viewModel = this@CalendarEmotionViewModel,
                yearMonth = yearMonth,
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

    fun onMonthlyListChange(list: List<MonthlyEmotion>) {
        _monthlyList.value = list
        Log.d("Monthly", "Updated monthly list: $list")
    }

    fun fetchDailyList(daily: String, onError: (String) -> Unit) {
        _isLoading.value = true
        viewModelScope.launch {
            getDailyList(
                viewModel = this@CalendarEmotionViewModel,
                yearMonth = daily,
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

    fun onDailyListChange(list: List<DailyEmotionResponse>) {
        _dailyList.value = list
        Log.d("Daily", "Updated daily list: $list")
    }
}