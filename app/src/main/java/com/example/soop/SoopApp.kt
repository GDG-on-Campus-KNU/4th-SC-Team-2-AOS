package com.example.soop

import android.app.Application
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.soop.database.EmotionDatabase
import com.example.soop.login.Splash3Screen
import com.example.soop.network.RetrofitInstance

class SoopApp : Application() {
    companion object {
        lateinit var securePrefs: SharedPreferences
            private set
    }

    override fun onCreate() {
        super.onCreate()

        // EncryptedSharedPreferences 초기화
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        securePrefs = EncryptedSharedPreferences.create(
            "secure_prefs",
            masterKeyAlias,
            applicationContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        // 데이터베이스 초기화
        EmotionDatabase.getDatabase(this)
        Log.d("EmotionDB", "Database created — inserting initial data")

        RetrofitInstance.init(this)

        // 자동 로그인 로직: 토큰 있으면 Main, 없으면 Splash3Screen
        val accessToken = securePrefs.getString("access_token", null)
        val nextActivity = if (!accessToken.isNullOrEmpty()) {
            Intent(this, MainActivity::class.java)
        } else {
            Intent(this, Splash3Screen::class.java)
        }
        nextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(nextActivity)
    }
}