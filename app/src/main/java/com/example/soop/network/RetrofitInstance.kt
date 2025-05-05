package com.example.soop.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton object to initialize Retrofit and provide ApiService instance.
 */
object RetrofitInstance {
    private const val BASE_URL = "http://3.34.0.32:8082/api/v1/" // TODO: Replace with your actual server URL

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
