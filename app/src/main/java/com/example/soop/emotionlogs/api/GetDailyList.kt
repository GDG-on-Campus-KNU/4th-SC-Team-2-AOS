package com.example.soop.emotionlogs.api

import android.util.Log
import com.example.soop.emotionlogs.viewmodel.CalendarEmotionViewModel
import com.example.soop.network.NetworkResult
import com.example.soop.network.RetrofitInstance
import com.example.soop.network.safeApiCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun getDailyList(
    viewModel: CalendarEmotionViewModel,
    yearMonth: String,
    onError: (String) -> Unit,
    onSuccess: () -> Unit = {}
) {
    CoroutineScope(Dispatchers.IO).launch {
        val dailyListResult = safeApiCall("DailyList") {
            RetrofitInstance.emotionLogApiService.getDailyList(yearMonth)
        }

        withContext(Dispatchers.Main) {
            when (dailyListResult) {
                is NetworkResult.Success -> {
                    viewModel.onDailyListChange(listOf(dailyListResult.data))
                    onSuccess()
                }
                is NetworkResult.Error -> {
                    Log.e("DailyList", "Daily 정보 불러오기 실패: ${dailyListResult.message}")
                    onError("Daily 정보 불러오기 실패: ${dailyListResult.message}")
                }
                else -> Unit
            }
        }
    }
}
