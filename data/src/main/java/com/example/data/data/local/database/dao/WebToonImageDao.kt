package com.example.data.data.local.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.data.local.database.entity.WebToonImage
import kotlinx.coroutines.flow.Flow

@Dao
interface WebToonImageDao {
    @Query("SELECT * FROM WebToonImage")
    fun getAllWebToonItem(): PagingSource<Int, WebToonImage>

    @Query("SELECT * FROM WebToonImage WHERE title LIKE '%' || :keyword || '%'")
    fun getWebToonItemByKeyword(keyword: String): PagingSource<Int, WebToonImage>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(images: List<WebToonImage>)

    @Query("DELETE FROM WebToonImage")
    suspend fun deleteAll()
}