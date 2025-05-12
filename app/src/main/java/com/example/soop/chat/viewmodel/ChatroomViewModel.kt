package com.example.soop.chat.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import com.example.soop.chat.data.ChatItemData
import java.time.LocalDateTime
import java.util.Date

class ChatroomViewModel : ViewModel() {

    private val _chatList = mutableStateListOf<ChatItemData>()
    val chatList: List<ChatItemData> get() = _chatList

    fun sendUserMessage(content: String) {
        _chatList.add(ChatItemData(content = content, senderId = 0, chatId = 0, chatRoomId = 0))

        // AI 응답 시뮬레이션 (간단한 delay 없이 처리)
        _chatList.add(ChatItemData(content = "AI 응답: \"$content\" 에 대한 답변입니다.", senderId = 0, chatId = 0, chatRoomId = 0))
    }
}
