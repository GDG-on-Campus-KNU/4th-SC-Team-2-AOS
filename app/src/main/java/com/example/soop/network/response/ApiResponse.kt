package com.example.soop.network

data class ApiResponse<T>(
    val code: String,
    val data: T,
    val message: String,
    val success: Boolean
)
