package com.example.soop.login

import android.content.Context
import android.util.Log
import com.example.soop.network.NetworkResult
import com.example.soop.network.RetrofitInstance
import com.example.soop.network.SecureStorage
import com.example.soop.network.request.LoginRequest
import com.example.soop.network.request.SignupRequest
import com.example.soop.network.response.LoginResponse
import com.example.soop.network.safeApiCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun registerThenLogin(
    context: Context,
    providerId: String,
    email: String,
    nickname: String,
    onSuccess: (String) -> Unit,
    onError: (String) -> Unit
) {
    CoroutineScope(Dispatchers.IO).launch {
        val signupResult = safeApiCall("Signup") {
            RetrofitInstance.apiService.registerUser(SignupRequest(providerId, email, nickname))
        }
        if (signupResult is NetworkResult.Error) {
            Log.d("LoginHelper", "회원가입 실패 또는 무시: ${signupResult.message}")
        }

        val loginResult = safeApiCall("Login") {
            RetrofitInstance.apiService.loginUser(LoginRequest(providerId, email))
        }

        withContext(Dispatchers.Main) {
            when (loginResult) {
                is NetworkResult.Success -> {
                    val tokens = loginResult.data
                    SecureStorage.saveTokens(context, tokens.accessToken, tokens.refreshToken)
                    onSuccess(tokens.accessToken)
                }
                is NetworkResult.Error -> {
                    onError("로그인 실패: ${loginResult.message}")
                }
                else -> Unit
            }
        }
    }
}
