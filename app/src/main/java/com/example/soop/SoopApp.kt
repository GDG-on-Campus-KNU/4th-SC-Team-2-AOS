package com.example.soop

import android.app.Application
import android.util.Log
import com.example.soop.database.EmotionDatabase

class SoopApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // 데이터베이스 초기화 트리거
        EmotionDatabase.getDatabase(this)
        Log.d("EmotionDB", "Database created — inserting initial data")
    }
}
