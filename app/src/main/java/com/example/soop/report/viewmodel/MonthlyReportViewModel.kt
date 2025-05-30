package com.example.soop.report.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soop.chat.api.getRecentlyList
import com.example.soop.chat.data.CustomChatbotData
import com.example.soop.chat.viewmodel.RecentlyViewModel
import com.example.soop.home.api.getMentalTip
import com.example.soop.home.data.HomeData
import com.example.soop.home.response.ExpertResponse
import com.example.soop.home.response.ExpertResponseWrapper
import com.example.soop.home.response.MentalTipResponseWrapper
import com.example.soop.report.response.MonthlyEmotionReportResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MonthlyReportViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(MonthlyEmotionReportResponse())
    val uiState: StateFlow<MonthlyEmotionReportResponse> = _uiState

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private var isInitialized = false

    fun onChangeMonthlyReport(new: MonthlyEmotionReportResponse) {
        _uiState.value = new
    }
}