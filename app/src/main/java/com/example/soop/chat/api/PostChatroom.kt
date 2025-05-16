package com.example.soop.chat.api

import com.example.soop.chat.request.ChatroomRequest
import android.util.Log
import com.example.soop.chat.response.ChatroomResponse
import com.example.soop.network.RetrofitInstance
import com.example.soop.network.NetworkResult
import com.example.soop.network.safeApiCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun postChatroom(
    chatRoomInfoId: Int,
    onSuccess: (ChatroomResponse) -> Unit,
    onError: (String) -> Unit
) {
    val requestBody = ChatroomRequest(chatRoomInfoId)

    CoroutineScope(Dispatchers.IO).launch {
        val chatroomResult = safeApiCall("Chatroom") {
            RetrofitInstance.chatApiService.postChatroom(requestBody)
        }

        withContext(Dispatchers.Main) {
            when (chatroomResult) {
                is NetworkResult.Success -> {
                    val response = chatroomResult.data
                    onSuccess(response)
                }
                is NetworkResult.Error -> {
                    onError("채팅생성 POST 실패: ${chatroomResult.message}")
                }
                else -> Unit
            }
        }
    }
}