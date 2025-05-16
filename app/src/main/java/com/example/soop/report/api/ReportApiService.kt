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
import com.example.soop.emotionlogs.response.MonthlyEmotion
import com.example.soop.login.request.LoginRequest
import com.example.soop.network.ApiResponse
import com.example.soop.report.response.FeedbackResponse
import com.example.soop.report.response.MonthlyEmotionReportResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ReportApiService {
    @GET("emotion-report/monthly")
    suspend fun getMonthlyReportList(): Response<ApiResponse<MonthlyEmotionReportResponse>>

    @GET("emotion-report/ai-feedback")
    suspend fun getFeedback(
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String
    ): Response<ApiResponse<FeedbackResponse>>
}
