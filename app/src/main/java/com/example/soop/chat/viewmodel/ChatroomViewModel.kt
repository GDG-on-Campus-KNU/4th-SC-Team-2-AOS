package com.example.soop.chat.viewmodel

import android.util.Log
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import com.example.soop.R
import com.example.soop.chat.response.ChatItemListWrapper
import com.example.soop.chat.response.ChatItemResponse
import com.example.soop.chat.response.RecentlyListResponse
import com.example.soop.chat.ui.ChatItem
import com.example.soop.network.RetrofitInstance.chatApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.json.JSONObject
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.StompClient
import ua.naiksoftware.stomp.dto.StompHeader
import ua.naiksoftware.stomp.dto.LifecycleEvent

class ChatroomViewModel : ViewModel() {

    private val _chatList = MutableStateFlow<List<ChatItemResponse>>(emptyList())
    val chatList: StateFlow<List<ChatItemResponse>> = _chatList.asStateFlow()

    private val _chatroomId = MutableStateFlow(0)
    val chatroomId: StateFlow<Int> = _chatroomId.asStateFlow()

    private val compositeDisposable = CompositeDisposable()
    private lateinit var stompClient: StompClient

    suspend fun loadInitialMessages(chatRoomId: Int) {
        try {
            val response = chatApiService.getMessages(chatRoomId)
            if (response.isSuccessful) {
                val body = response.body()
                val chats = body?.data?.chats ?: emptyList()
                _chatList.value = chats
            } else {
                Log.e("SOCKET", "서버 응답 오류: ${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            Log.e("SOCKET", "메시지 초기화 실패", e)
        }
    }

    fun onChangeChatroomId(newId: Int) {
        _chatroomId.value = newId
    }

    fun onMessageChange(list: List<ChatItemResponse>) {
        _chatList.value = list
        Log.d("Recently", "Updated recently list: $list")
    }

    fun connectWebSocket(jwtToken: String, ip: String) {
        stompClient = Stomp.over(
            Stomp.ConnectionProvider.OKHTTP,
            "ws://$ip/ws/chat/websocket"
        )
        val headers = listOf(StompHeader("Authorization", "Bearer $jwtToken"))
        stompClient.connect(headers)

        compositeDisposable.add(
            stompClient.lifecycle()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { event ->
                    when (event.type) {
                        LifecycleEvent.Type.OPENED -> Log.i("SOCKET", "✅ Connected")
                        LifecycleEvent.Type.ERROR -> Log.e("SOCKET", "❌ Error", event.exception)
                        LifecycleEvent.Type.CLOSED -> Log.i("SOCKET", "🛑 Closed")
                        else -> Log.d("SOCKET", "ℹ️ ${event.message}")
                    }
                }
        )
        Log.i("SOCKET", "Connecting to chatroomId: ${_chatroomId.value}")
        compositeDisposable.add(
            stompClient.topic("/sub/chatroom/${_chatroomId.value}")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ topicMessage ->
                    Log.d("SOCKET", "수신 메시지: ${topicMessage.payload}")
                    val json = JSONObject(topicMessage.payload)
                    val message = ChatItemResponse(
                        chatId = json.getString("chatId"),
                        chatRoomId = json.getInt("chatRoomId"),
                        senderId = json.getInt("senderId"),
                        content = json.getString("content"),
                        createdAt = json.getString("createdAt")
                    )
                    _chatList.update { it + message }
                }, {
                    Log.e("SOCKET", "❌ 구독 실패", it)
                })
        )
    }

    fun sendMessage(message: String) {
        if (!stompClient.isConnected) {
            Log.w("SOCKET", "🚫 연결되지 않음")
            return
        }

        val payload = JSONObject().apply {
            put("chatRoomId", _chatroomId.value)
            put("content", message)
        }

        compositeDisposable.add(
            stompClient.send("/pub/chat", payload.toString())
                .subscribe({
                    Log.i("SOCKET", "메시지 전송 성공")
                }, {
                    Log.e("SOCKET", "메시지 전송 실패", it)
                })
        )
    }

    override fun onCleared() {
        stompClient.disconnect()
        compositeDisposable.clear() // 🧹 모든 Disposable 정리
        super.onCleared()
    }
}
