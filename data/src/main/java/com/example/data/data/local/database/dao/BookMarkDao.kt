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
    suspend fun addBookMarkItems(item: List<BookMark>)

    @Query("DELETE FROM BookMark")
    suspend fun removeAllBookMarkItems()
}