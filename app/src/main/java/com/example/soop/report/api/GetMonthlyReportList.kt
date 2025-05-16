package com.example.soop.report.api

import android.content.Context
import android.util.Log
import com.example.soop.home.viewmodel.HomeViewModel
import com.example.soop.network.RetrofitInstance
import com.example.soop.network.SecureStorage
import com.example.soop.login.request.LoginRequest
import com.example.soop.login.request.SignupRequest
import com.example.soop.network.NetworkResult
import com.example.soop.network.safeApiCall
import com.example.soop.report.viewmodel.MonthlyReportViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun getMonthlyReportList(
    viewModel: MonthlyReportViewModel,
    onError: (String) -> Unit
) {
    CoroutineScope(Dispatchers.IO).launch {
        val monthlyResult = safeApiCall("UserInfo") {
            RetrofitInstance.reportApiService.getMonthlyReportList()
        }

        withContext(Dispatchers.Main) {
            when (monthlyResult) {
                is NetworkResult.Success -> {
                    val monthlyReport = monthlyResult.data
                    Log.d("Report", "${monthlyResult}")
                    viewModel.onChangeMonthlyReport(monthlyReport)
                }
                is NetworkResult.Error -> {
                    Log.e("Report", "내 정보 불러오기 실패: ${monthlyResult.message}")
                    onError("내 정보 불러오기 실패: ${monthlyResult.message}")
                }
                else -> Unit
            }
        }
    }
}