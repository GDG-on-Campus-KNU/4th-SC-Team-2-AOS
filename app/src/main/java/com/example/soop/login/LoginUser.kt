package com.example.soop.login

import android.content.Context
import android.util.Log
import com.example.soop.network.RetrofitInstance
import com.example.soop.network.SecureStorage
import com.example.soop.network.request.LoginRequest
import com.example.soop.network.request.SignupRequest
import com.example.soop.network.response.LoginResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * 회원가입 시도 후, 가입 성공/실패와 관계없이 로그인 진행
 * onSuccess: accessToken 을 반환
 * onError: 메시지 반환
 */
fun registerThenLogin(
    context: Context,
    providerId: String,
    email: String,
    nickname: String,
    onSuccess: (accessToken: String) -> Unit,
    onError: (message: String) -> Unit
) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            // 회원가입 요청 (이미 가입된 경우 실패하더라도 무시)
            val signupReq = SignupRequest(providerId, email, nickname)
            val signupResp = RetrofitInstance.apiService.registerUser(signupReq)
            if (!(signupResp.isSuccessful && signupResp.body()?.success == true)) {
                Log.d("LoginHelper", "회원가입 무시 또는 실패: ${signupResp.code()}")
            }
            // 로그인 요청
            val loginReq = LoginRequest(providerId = providerId, email = email)
            val loginResp = RetrofitInstance.apiService.loginUser(loginReq)
            if (loginResp.isSuccessful && loginResp.body()?.success == true) {
                val tokens: LoginResponse = loginResp.body()!!.data
                // 토큰 저장
                SecureStorage.saveTokens(
                    context = context,
                    accessToken = tokens.accessToken,
                    refreshToken = tokens.refreshToken
                )
                withContext(Dispatchers.Main) {
                    onSuccess(tokens.accessToken)
                }
            } else {
                withContext(Dispatchers.Main) {
                    onError("로그인 실패: ${loginResp.body()?.message ?: loginResp.message()}")
                }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                onError("네트워크 오류: ${e.localizedMessage}")
            }
        }
    }
}