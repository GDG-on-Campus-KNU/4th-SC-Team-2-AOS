package com.example.soop.chat.api

import android.content.Context
import android.util.Log
import com.example.soop.chat.viewmodel.ChatbotViewModel
import com.example.soop.chat.viewmodel.ChatroomViewModel
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

fun getMessage(
    chatRoomId: Int,
    viewModel: ChatroomViewModel ,
    onError: (String) -> Unit,
    onSuccess: () -> Unit = {}
) {
    CoroutineScope(Dispatchers.IO).launch {
        val messageResult = safeApiCall("Message") {
            RetrofitInstance.chatApiService.getMessages(chatRoomId)
        }

        withContext(Dispatchers.Main) {
            when (messageResult) {
                is NetworkResult.Success -> {
                    viewModel.onMessageChange(messageResult.data?.chats?:emptyList())
                    onSuccess()
                }
                is NetworkResult.Error -> {
                    Log.e("RecentlyList", "최근 리스트 정보 불러오기 실패: ${messageResult.message}")
                    onError("최근 리스트 정보 불러오기 실패: ${messageResult.message}")
                }
                else -> Unit
            }
        }
    }
}
