package com.example.soop.login.api

import com.example.soop.network.ApiResponse
import com.example.soop.login.request.LoginRequest
import com.example.soop.login.request.SignupRequest
import com.example.soop.login.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {
    @POST("users/signup/general")
    suspend fun registerUser(@Body body: SignupRequest): Response<ApiResponse<String>>

    @POST("users/login")
    suspend fun loginUser(@Body body: LoginRequest): Response<ApiResponse<LoginResponse>>
}
