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
                                        Emotion(name = "sadness", imageIdx = 0),
                                        Emotion(name = "anxiety", imageIdx = 1),
                                        Emotion(name = "joy", imageIdx = 2),
                                        Emotion(name = "gratitude", imageIdx = 3),
                                        Emotion(name = "relief", imageIdx = 4),
                                        Emotion(name = "boredom", imageIdx = 5)
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
