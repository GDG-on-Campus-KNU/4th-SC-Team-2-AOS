package com.example.soop.network

import android.util.Log
import retrofit2.Response

suspend fun <T> safeApiCall(
    tag: String = "ApiCall",
    apiCall: suspend () -> Response<ApiResponse<T>>
): NetworkResult<T> {
    return try {
        val response = apiCall()
        logResponse(response, tag)
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null && body.success && body.data != null) {
                NetworkResult.Success(body.data)
            } else {
                NetworkResult.Error(
                    message = "[${body?.code}] ${body?.message ?: "No message"}",
                    code = response.code()
                )
            }
        } else {
            NetworkResult.Error(
                message = "HTTP ${response.code()} ${response.message()}",
                code = response.code()
            )
        }
    } catch (e: Exception) {
        Log.e(tag, "Network exception", e)
        NetworkResult.Error(message = e.localizedMessage ?: "Network error")
    }
}

private fun <T> logResponse(response: Response<ApiResponse<T>>, tag: String) {
    val code = response.code()
    val msg = response.message()
    val success = response.isSuccessful
    Log.d(tag, "HTTP Status: $code, message: $msg, success: $success")
    response.body()?.let { body ->
        Log.d(tag, "Body.code: ${body.code}")
        Log.d(tag, "Body.message: ${body.message}")
        Log.d(tag, "Body.success: ${body.success}")
        Log.d(tag, "Body.data: ${body.data}")
    } ?: Log.d(tag, "Empty body")
}