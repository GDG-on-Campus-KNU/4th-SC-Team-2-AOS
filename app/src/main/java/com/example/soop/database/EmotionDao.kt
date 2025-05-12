package com.example.soop.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EmotionDao {
    @Insert
    suspend fun insertAll(emotions: List<Emotion>)

    @Insert
    suspend fun insert(emotion: Emotion)

    @Query("SELECT * FROM emotion_table ORDER BY id ASC")
    suspend fun getAllEmotions(): List<Emotion>
}
