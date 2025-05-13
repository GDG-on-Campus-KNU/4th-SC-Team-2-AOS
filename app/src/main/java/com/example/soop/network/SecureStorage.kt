package com.example.soop.network

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

object SecureStorage {
    private const val PREF_NAME = "secure_prefs"

    private fun getPrefs(context: Context) =
        EncryptedSharedPreferences.create(
            PREF_NAME,
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
            context.applicationContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    fun saveTokens(context: Context, accessToken: String, refreshToken: String) {
        getPrefs(context).edit()
            .putString("access_token", accessToken)
            .putString("refresh_token", refreshToken)
            .apply()
    }

    fun getAccessToken(context: Context): String? =
        getPrefs(context).getString("access_token", null)

    fun getRefreshToken(context: Context): String? =
        getPrefs(context).getString("refresh_token", null)

    fun clearTokens(context: Context) {
        getPrefs(context).edit().clear().apply()
    }
}
