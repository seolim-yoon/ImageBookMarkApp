package com.example.data.datasource.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookMarkDao {
    @Query("SELECT * FROM BookMark")
    fun getAllBookMarkItem(): Flow<List<BookMark>>

    @Query("SELECT image_id FROM BookMark")
    suspend fun getAllBookMarkItemIds(): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookMarkItem(item: BookMark): Long

    @Delete
    suspend fun removeBookMarkItem(item: BookMark): Int
}