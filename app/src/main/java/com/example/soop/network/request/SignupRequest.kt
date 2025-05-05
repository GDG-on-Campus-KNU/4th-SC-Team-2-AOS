package com.example.soop.network.request

data class SignupRequest(
    val providerId: String,
    val email: String,
    val nickname: String,
)