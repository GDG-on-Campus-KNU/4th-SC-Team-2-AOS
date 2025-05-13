package com.example.soop.network.response

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String
)