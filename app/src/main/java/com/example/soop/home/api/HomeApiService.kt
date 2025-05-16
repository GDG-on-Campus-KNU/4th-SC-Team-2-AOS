package com.example.soop.home.api

import android.service.autofill.UserData
import com.example.soop.home.response.UserResponse
import com.example.soop.network.ApiResponse
import com.example.soop.login.request.LoginRequest
import com.example.soop.login.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface HomeApiService {
    @GET("users/me")
    suspend fun getUserInfo(): Response<ApiResponse<UserResponse>>

    @POST("users/login")
    suspend fun loginUser(@Body body: LoginRequest): Response<ApiResponse<LoginResponse>>
}
