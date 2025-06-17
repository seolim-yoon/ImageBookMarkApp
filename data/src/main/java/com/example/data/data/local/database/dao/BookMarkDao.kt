package com.example.data.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.data.local.database.entity.BookMark
import kotlinx.coroutines.flow.Flow

@Dao
interface BookMarkDao {
    @Query("SELECT * FROM BookMark")
    fun getAllBookMarkItem(): Flow<List<BookMark>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookMarkItem(item: BookMark): Long

    @Delete
    suspend fun removeBookMarkItems(items: List<BookMark>): Int
}