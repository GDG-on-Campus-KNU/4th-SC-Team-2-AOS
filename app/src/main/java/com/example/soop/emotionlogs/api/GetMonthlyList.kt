package com.example.soop.emotionlogs.api

import android.util.Log
import com.example.soop.chat.viewmodel.ChatbotViewModel
import com.example.soop.emotionlogs.request.MonthlyEmotionRequest
import com.example.soop.emotionlogs.viewmodel.CalendarEmotionViewModel
import com.example.soop.home.viewmodel.HomeViewModel
import com.example.soop.network.RetrofitInstance
import com.example.soop.network.SecureStorage
import com.example.soop.login.request.LoginRequest
import com.example.soop.login.request.SignupRequest
import com.example.soop.network.NetworkResult
import com.example.soop.network.safeApiCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun getMonthlyList(
    viewModel: CalendarEmotionViewModel,
    yearMonth: String,
    onError: (String) -> Unit,
    onSuccess: () -> Unit = {}
) {
    CoroutineScope(Dispatchers.IO).launch {
        val monthlyListResult = safeApiCall("MonthlyList") {
            RetrofitInstance.emotionLogApiService.getMonthlyList(yearMonth = "2025-05")
        }

        withContext(Dispatchers.Main) {
            when (monthlyListResult) {
                is NetworkResult.Success -> {
                    viewModel.onMonthlyListChange(monthlyListResult.data)
                    onSuccess()
                }
                is NetworkResult.Error -> {
                    Log.e("MonthlyList", "Monthly 정보 불러오기 실패: ${monthlyListResult.message}")
                    onError("Monthly 정보 불러오기 실패: ${monthlyListResult.message}")
                }
                else -> Unit
            }
        }
    }
}
