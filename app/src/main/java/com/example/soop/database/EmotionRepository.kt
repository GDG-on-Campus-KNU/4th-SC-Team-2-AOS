package com.example.soop.database

import android.content.Context
import androidx.room.Room

class EmotionRepository(context: Context) {
    private val emotionDao: EmotionDao =
        EmotionDatabase.getDatabase(context).emotionDao()

    suspend fun insertEmotion(emotion: Emotion) {
        emotionDao.insert(emotion)
    }

    suspend fun getAllEmotions(): List<Emotion> {
        return emotionDao.getAllEmotions()
    }
}

