package com.example.soop.home.api

import android.content.Context
import android.util.Log
import com.example.soop.home.viewmodel.HomeViewModel
import com.example.soop.network.RetrofitInstance
import com.example.soop.network.SecureStorage
import com.example.soop.login.request.LoginRequest
import com.example.soop.login.request.SignupRequest
import com.example.soop.network.NetworkResult
import com.example.soop.network.safeApiCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun getUserInfo(
    viewModel: HomeViewModel,
    onError: (String) -> Unit
) {
    CoroutineScope(Dispatchers.IO).launch {
        val userResult = safeApiCall("UserInfo") {
            RetrofitInstance.homeApiService.getUserInfo()
        }

        withContext(Dispatchers.Main) {
            when (userResult) {
                is NetworkResult.Success -> {
                    val userProfile = userResult.data
                    Log.d("UserInfo", "이름: ${userProfile.nickName}, 이메일: ${userProfile.email}")
                    viewModel.onNameChange(userProfile.nickName)
                }
                is NetworkResult.Error -> {
                    Log.e("UserInfo", "내 정보 불러오기 실패: ${userResult.message}")
                    onError("내 정보 불러오기 실패: ${userResult.message}")
                }
                else -> Unit
            }
        }
    }
}