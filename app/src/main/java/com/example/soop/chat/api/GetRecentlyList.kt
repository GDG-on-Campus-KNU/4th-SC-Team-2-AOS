package com.example.soop.chat.api

import android.content.Context
import android.util.Log
import com.example.soop.chat.viewmodel.ChatbotViewModel
import com.example.soop.chat.viewmodel.RecentlyViewModel
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

fun getRecentlyList(
    viewModel: RecentlyViewModel,
    onError: (String) -> Unit,
    onSuccess: () -> Unit = {}
) {
    CoroutineScope(Dispatchers.IO).launch {
        val recentlyListResult = safeApiCall("RecentlyList") {
            RetrofitInstance.chatApiService.getRecentlyList()
        }

        withContext(Dispatchers.Main) {
            when (recentlyListResult) {
                is NetworkResult.Success -> {
                    viewModel.onRecentlyListChange(recentlyListResult.data?.chatRooms ?: emptyList())
                    onSuccess()
                }
                is NetworkResult.Error -> {
                    Log.e("RecentlyList", "최근 리스트 정보 불러오기 실패: ${recentlyListResult.message}")
                    onError("최근 리스트 정보 불러오기 실패: ${recentlyListResult.message}")
                }
                else -> Unit
            }
        }
    }
}
