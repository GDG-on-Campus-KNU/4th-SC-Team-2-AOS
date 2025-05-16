package com.example.soop.home.viewmodel

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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(HomeData())
    val uiState: StateFlow<HomeData> = _uiState

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private var isInitialized = false

    fun onNameChange(new: String) {
        _uiState.update { it.copy(nickName = new) }
    }

    fun onMentalTipChange(newTips: MentalTipResponseWrapper) {
        _uiState.update { current ->
            current.copy(mentaltipList = newTips)
        }
        setLoading(false)
    }

    fun setLoading(loading: Boolean) {
        _isLoading.value = loading
    }

    fun loadMentalTip(
        onError: (String) -> Unit = {},
        onSuccess: () -> Unit = {}
    ) {
        getMentalTip(this, onError, onSuccess)
    }
}