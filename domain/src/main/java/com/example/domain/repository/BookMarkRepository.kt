package com.example.domain.repository

import com.example.domain.entity.ImageEntity
import kotlinx.coroutines.flow.Flow

interface BookMarkRepository {
    fun getAllBookMarkItem(): Flow<List<ImageEntity>>

    suspend fun replaceBookmarkItems(items: List<ImageEntity>)
}