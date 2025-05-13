package com.example.soop.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "emotion_table")
data class Emotion(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val imageIdx: Int = 0,
    val name: String = "joy",
    val group: String = "POSITIVE"
)
