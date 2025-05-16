package com.example.soop.chat.api

import android.content.Context
import android.util.Log
import com.example.soop.chat.viewmodel.ChatbotViewModel
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

fun getChatbotList(
    viewModel: ChatbotViewModel,
    onError: (String) -> Unit,
    onSuccess: () -> Unit = {}
) {
    CoroutineScope(Dispatchers.IO).launch {
        val chatbotListResult = safeApiCall("ChatbotList") {
            RetrofitInstance.chatApiService.getChatbotList()
        }

        withContext(Dispatchers.Main) {
            when (chatbotListResult) {
                is NetworkResult.Success -> {
                    viewModel.onChatbotListChange(chatbotListResult.data)
                    onSuccess()
                }
                is NetworkResult.Error -> {
                    Log.e("ChatbotList", "챗봇 리스트 정보 불러오기 실패: ${chatbotListResult.message}")
                    onError("챗봇 리스트 정보 불러오기 실패: ${chatbotListResult.message}")
                }
                else -> Unit
            }
        }
    }
}
