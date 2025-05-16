package com.example.soop.home.response

data class ExpertResponse(
    val id: Int = 0,
    val providerId: String = "",
    val email: String = "",
    val nickname: String = "",
    val userType: String = "USER",
    val category: String = "DOCTOR",
    val experience: Int = 0,
    val styles: List<String> = emptyList(),
    val language: String = "ENGLISH",
    val bio: String = "",
    val image: Int = 0
)

data class ExpertResponseWrapper(
    val data: List<ExpertResponse> = emptyList()
)
