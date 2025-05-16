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
import com.example.soop.report.viewmodel.FeedbackModel
import com.example.soop.report.viewmodel.MonthlyReportViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate

fun getFeedback(
    viewModel: FeedbackModel,
    onError: (String) -> Unit
) {
    val startDate = LocalDate.now().withDayOfMonth(1).toString()
    val endDate = LocalDate.now().toString()

    CoroutineScope(Dispatchers.IO).launch {
        val feedbackResult = safeApiCall("Feedback") {
            RetrofitInstance.reportApiService.getFeedback(startDate = startDate, endDate = endDate)
        }

        withContext(Dispatchers.Main) {
            when (feedbackResult) {
                is NetworkResult.Success -> {
                    val feedbackReport = feedbackResult.data
                    Log.d("Feedback", "${feedbackResult}")
                    viewModel.onChangeFeedback(feedbackReport)
                }
                is NetworkResult.Error -> {
                    Log.e("Feedback", "내 정보 불러오기 실패: ${feedbackResult.message}")
                    onError("내 정보 불러오기 실패: ${feedbackResult.message}")
                }
                else -> Unit
            }
        }
    }
}