package com.example.soop.home.api

import android.service.autofill.UserData
import com.example.soop.chat.response.ChatItemListWrapper
import com.example.soop.home.response.ExpertResponse
import com.example.soop.home.response.ExpertResponseWrapper
import com.example.soop.home.response.MentalTipResponseWrapper
import com.example.soop.home.response.UserResponse
import com.example.soop.network.ApiResponse
import com.example.soop.login.request.LoginRequest
import com.example.soop.login.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface HomeApiService {
    @GET("users/me")
    suspend fun getUserInfo(): Response<ApiResponse<UserResponse>>

    @GET("mental-tip")
    suspend fun getMentalTip(): Response<MentalTipResponseWrapper>

    @GET("users/experts")
    suspend fun getExpertList(): Response<ApiResponse<List<ExpertResponse>>>
}
