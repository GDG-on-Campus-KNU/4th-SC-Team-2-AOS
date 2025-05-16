package com.example.soop.chat.api

import android.util.Log
import com.example.soop.chat.request.CustomChatbotRequest
import com.example.soop.login.request.LoginRequest
import com.example.soop.network.RetrofitInstance
import com.example.soop.network.NetworkResult
import com.example.soop.network.safeApiCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun postCustomChatbot(
    name: String,
    description: String,
    empathyLevel: String,
    tone: String,
    image: Int,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
) {
    val requestBody = CustomChatbotRequest(name, description, empathyLevel, tone, image)
    Log.d("CustomChatbot", "보낼 데이터: ${requestBody}")

    CoroutineScope(Dispatchers.IO).launch {
        val customChatbotResult = safeApiCall("CustomChatbot") {
            RetrofitInstance.chatApiService.postCustomChatbot(requestBody)
        }
        if (customChatbotResult is NetworkResult.Error) {
            Log.d("CustomChatbot", "커스텀 챗봇 POST 실패1: ${customChatbotResult.message}")
        }

        withContext(Dispatchers.Main) {
            when (customChatbotResult) {
                is NetworkResult.Success -> {
                    onSuccess()
                }
                is NetworkResult.Error -> {
                    onError("커스텀 챗봇 POST 실패2: ${customChatbotResult.message}")
                }
                else -> Unit
            }
        }
    }
}
