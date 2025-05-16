package com.example.soop.emotionlogs.api

import com.example.soop.chat.request.ChatroomRequest
import android.util.Log
import com.example.soop.chat.response.ChatroomResponse
import com.example.soop.emotionlogs.request.EmotionLogRequest
import com.example.soop.network.RetrofitInstance
import com.example.soop.network.NetworkResult
import com.example.soop.network.safeApiCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

fun postEmotionLog(
    name: String,
    group: String,
    content: String,
    recordedAt: String,
    image: Int,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
) {
    val requestBody = EmotionLogRequest(name, group, content, recordedAt, image)

    CoroutineScope(Dispatchers.IO).launch {
        Log.d("postEmotionLog", "Sending request: $requestBody")

        val postEmotionResult = safeApiCall("EmotionLog") {
            RetrofitInstance.emotionLogApiService.postEmotionLog(requestBody)
        }

        withContext(Dispatchers.Main) {
            when (postEmotionResult) {
                is NetworkResult.Success -> {
                    Log.d("postEmotionLog", "Success")
                    onSuccess() // 응답값 없이 콜백만 호출
                }
                is NetworkResult.Error -> {
                    Log.e("postEmotionLog", "Error: ${postEmotionResult.message}")
                    onError("EmotionLog POST 실패: ${postEmotionResult.message}")
                }
                else -> {
                    Log.w("postEmotionLog", "Unexpected state")
                }
            }
        }
    }
}
