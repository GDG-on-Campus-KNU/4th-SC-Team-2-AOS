package com.example.soop.chat.api

import android.app.DownloadManager.Request
import android.os.Message
import com.example.soop.chat.request.ChatbotRoomRequest
import com.example.soop.chat.request.ChatroomRequest
import com.example.soop.chat.request.CustomChatbotRequest
import com.example.soop.chat.response.ChatItemListWrapper
import com.example.soop.chat.response.ChatItemResponse
import com.example.soop.chat.response.ChatbotListResponse
import com.example.soop.chat.response.ChatroomResponse
import com.example.soop.chat.response.RecentlyListResponse
import com.example.soop.chat.response.RecentlyListWrapper
import com.example.soop.login.request.LoginRequest
import com.example.soop.network.ApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ChatApiService {
    @GET("chat/bots")
    suspend fun getChatbotList(): Response<ApiResponse<List<ChatbotListResponse>>>

    @GET("chat/rooms/ai")
    suspend fun getRecentlyList(): Response<ApiResponse<RecentlyListWrapper>>

    @POST("chat/bots")
    suspend fun postCustomChatbot(@Body body: CustomChatbotRequest): Response<ApiResponse<String>>

    @POST("chat/rooms/ai")
    suspend fun postChatroom(@Body body: ChatroomRequest): Response<ApiResponse<ChatroomResponse>>

    @GET("chat/{chatRoomId}/messages")
    suspend fun getMessages(@Path("chatRoomId") chatRoomId: Int): Response<ApiResponse<ChatItemListWrapper>>
}
