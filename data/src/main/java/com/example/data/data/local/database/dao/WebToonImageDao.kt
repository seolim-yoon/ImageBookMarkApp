package com.example.data.data.local.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.data.data.local.database.entity.WebToonImage
import kotlinx.coroutines.flow.Flow

@Dao
interface WebToonImageDao {
    @Query("SELECT * FROM WebToonImage")
    fun getAllWebToonItems(): PagingSource<Int, WebToonImage>

    @Query("SELECT * FROM WebToonImage WHERE title LIKE '%' || :keyword || '%'")
    fun getWebToonItemByKeyword(keyword: String): PagingSource<Int, WebToonImage>

    @Query("SELECT link FROM WebToonImage")
    fun getAllWebToonItemUrls(): PagingSource<Int, String>

    @Query("SELECT * FROM WebToonImage WHERE isBookMark = 1")
    fun getAllBookMarkItems(): Flow<List<WebToonImage>>

    @Query("UPDATE WebToonImage SET isBookMark = 1 WHERE image_id IN (:ids)")
    suspend fun addBookMarkItems(ids: List<String>)

    @Query("UPDATE WebToonImage SET isBookMark = 0 WHERE image_id IN (:ids)")
    suspend fun removeBookMarkItems(ids: List<String>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(images: List<WebToonImage>)

    @Query("DELETE FROM WebToonImage")
    suspend fun deleteAll()
}