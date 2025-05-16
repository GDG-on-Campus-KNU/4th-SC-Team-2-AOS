package com.example.soop.emotionlogs.api

import com.example.soop.emotionlogs.request.EmotionLogRequest
import com.example.soop.emotionlogs.request.MonthlyEmotionRequest
import com.example.soop.emotionlogs.response.DailyEmotionResponse
import com.example.soop.emotionlogs.response.MonthlyEmotion
import com.example.soop.login.request.LoginRequest
import com.example.soop.network.ApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface EmotionLogApiService {
    @POST("emotion-logs")
    suspend fun postEmotionLog(@Body body: EmotionLogRequest): Response<ApiResponse<String>>

    @GET("emotion-logs/monthly")
    suspend fun getMonthlyList(@Query("yearMonth") yearMonth: String): Response<ApiResponse<List<MonthlyEmotion>>>

    @GET("emotion-logs/daily")
    suspend fun getDailyList(@Query("localDate") localDate: String): Response<ApiResponse<DailyEmotionResponse>>
}
