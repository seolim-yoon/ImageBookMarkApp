package com.example.data.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.data.local.database.dao.WebToonImageDao
import com.example.data.data.local.database.entity.WebToonImage

@Database(entities = [WebToonImage::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun webToonImageDao(): WebToonImageDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(
            context: Context
        ): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}