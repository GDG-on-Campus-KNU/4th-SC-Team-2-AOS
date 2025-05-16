package com.example.soop.home.api

import android.util.Log
import com.example.soop.home.response.MentalTipResponseWrapper
import com.example.soop.home.viewmodel.HomeViewModel
import com.example.soop.network.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

fun getMentalTip(
    viewModel: HomeViewModel,
    onError: (String) -> Unit,
    onSuccess: () -> Unit = {}
) {
    CoroutineScope(Dispatchers.IO).launch {
        viewModel.setLoading(true)
        try {
            val response = RetrofitInstance.homeApiService.getMentalTip()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val body: MentalTipResponseWrapper? = response.body()
                    if (body != null) {
                        viewModel.onMentalTipChange(body)
                        onSuccess()
                    } else {
                        viewModel.setLoading(false)
                        val msg = "응답 본문이 null입니다."
                        onError(msg)
                        Log.e("MentalTipResult", msg)
                    }
                } else {
                    viewModel.setLoading(false)
                    val msg = "멘탈팁 불러오기 실패: ${response.code()} - ${response.message()}"
                    onError(msg)
                    Log.e("MentalTipResult", msg)
                }
            }
        } catch (e: IOException) {
            withContext(Dispatchers.Main) {
                viewModel.setLoading(false)
                val msg = "네트워크 오류: ${e.localizedMessage}"
                onError(msg)
                Log.e("MentalTipResult", msg, e)
            }
        } catch (e: HttpException) {
            withContext(Dispatchers.Main) {
                viewModel.setLoading(false)
                val msg = "HTTP 예외: ${e.code()} - ${e.message()}"
                onError(msg)
                Log.e("MentalTipResult", msg, e)
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                viewModel.setLoading(false)
                val msg = "알 수 없는 오류 발생: ${e.localizedMessage}"
                onError(msg)
                Log.e("MentalTipResult", msg, e)
            }
        }
    }
}
