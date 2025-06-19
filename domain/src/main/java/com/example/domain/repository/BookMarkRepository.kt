package com.example.domain.repository

import com.example.domain.entity.ImageEntity
import kotlinx.coroutines.flow.Flow

interface BookMarkRepository {
    fun getAllBookMarkItems(): Flow<List<ImageEntity>>
    suspend fun addBookMarkItems(ids: List<String>)
    suspend fun removeBookMarkItems(ids: List<String>)
}