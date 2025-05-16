package com.example.soop.login.request

data class SignupRequest(
    val providerId: String,
    val email: String,
    val nickname: String,
)