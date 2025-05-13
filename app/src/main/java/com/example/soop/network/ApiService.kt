package com.example.soop.network

import com.example.soop.network.request.LoginRequest
import com.example.soop.network.request.SignupRequest
import com.example.soop.network.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Retrofit API interface for authentication endpoints.
 */
interface ApiService {
    @POST("users/signup/general")
    suspend fun registerUser(@Body body: SignupRequest): Response<ApiResponse<String>>

    @POST("users/login")
    suspend fun loginUser(@Body body: LoginRequest): Response<ApiResponse<LoginResponse>>
}
