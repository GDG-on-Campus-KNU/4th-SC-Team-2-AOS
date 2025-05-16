package com.example.soop.home.api

import android.util.Log
import com.example.soop.chat.viewmodel.ChatbotViewModel
import com.example.soop.chat.viewmodel.RecentlyViewModel
import com.example.soop.chat.viewmodel.RecommendedExpertViewModel
import com.example.soop.home.response.ExpertResponseWrapper
import com.example.soop.home.response.MentalTipResponseWrapper
import com.example.soop.home.viewmodel.HomeViewModel
import com.example.soop.network.RetrofitInstance
import com.example.soop.network.NetworkResult
import com.example.soop.network.safeApiCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun getExpertList(
    viewModel: RecommendedExpertViewModel,
    onError: (String) -> Unit,
    onSuccess: () -> Unit = {}
) {
    CoroutineScope(Dispatchers.IO).launch {
        val expertResult = safeApiCall("ExpertListResult") {
            RetrofitInstance.homeApiService.getExpertList()
        }

        withContext(Dispatchers.Main) {
            when (expertResult) {
                is NetworkResult.Success -> {
                    val expertList = ExpertResponseWrapper(data = expertResult.data ?: emptyList())
                    viewModel.onExpertListChange(expertList)
                }
                is NetworkResult.Error -> {
                    Log.e("ExpertListResult", "전문가목록 정보 불러오기 실패: ${expertResult.message}")
                    onError("전문가목록 정보 불러오기 실패: ${expertResult.message}")
                }
                else -> Unit
            }
        }
    }
}
