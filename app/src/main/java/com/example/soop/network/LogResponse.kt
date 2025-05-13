package com.example.soop.network

import android.util.Log
import retrofit2.Response

fun Response<*>.LogResponse(tag: String = "ApiResponse") {
    val body = this.body()
    val code = this.code()
    val message = this.message()
    val isSuccessful = this.isSuccessful

    Log.d(tag, "---------- HTTP 응답 ----------")
    Log.d(tag, "HTTP Status Code: $code")
    Log.d(tag, "HTTP Message: $message")
    Log.d(tag, "Is Successful: $isSuccessful")

    if (body is Map<*, *>) {
        Log.d(tag, "Body.code: ${body["code"]}")
        Log.d(tag, "Body.data: ${body["data"]}")
        Log.d(tag, "Body.message: ${body["message"]}")
        Log.d(tag, "Body.success: ${body["success"]}")
    } else if (body != null) {
        try {
            val responseMap = body as? Map<String, Any>
            if (responseMap != null) {
                Log.d(tag, "Body.code: ${responseMap["code"]}")
                Log.d(tag, "Body.data: ${responseMap["data"]}")
                Log.d(tag, "Body.message: ${responseMap["message"]}")
                Log.d(tag, "Body.success: ${responseMap["success"]}")
            } else {
                Log.d(tag, "Body: $body (비 Map 형태)")
            }
        } catch (e: Exception) {
            Log.e(tag, "Body 파싱 중 오류", e)
        }
    } else {
        Log.d(tag, "응답 Body가 null입니다.")
    }

    Log.d(tag, "--------------------------------")
}