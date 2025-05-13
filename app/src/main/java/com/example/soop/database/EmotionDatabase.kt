package com.example.soop.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Emotion::class], version = 1)
abstract class EmotionDatabase : RoomDatabase() {
    abstract fun emotionDao(): EmotionDao

    companion object {
        @Volatile private var INSTANCE: EmotionDatabase? = null

        fun getDatabase(context: Context): EmotionDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EmotionDatabase::class.java,
                    "emotion_db"
                )
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            CoroutineScope(Dispatchers.IO).launch {
                                getDatabase(context).emotionDao().insertAll(
                                    listOf(
                                        Emotion(name = "Joy", imageIdx = 0, group = "POSITIVE"),
                                        Emotion(name = "Gratitude", imageIdx = 1, group = "POSITIVE"),
                                        Emotion(name = "Calm", imageIdx = 2, group = "POSITIVE"),
                                        Emotion(name = "Sadness", imageIdx = 3, group = "NEGATIVE"),
                                        Emotion(name = "Anger", imageIdx = 4, group = "NEGATIVE"),
                                        Emotion(name = "Anxiety", imageIdx = 5, group = "NEGATIVE"),
                                        Emotion(name = "Surprise", imageIdx = 6, group = "NEUTRAL"),
                                    )
                                )
                            }
                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
