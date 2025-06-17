package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.entity.ImageEntity
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    fun getImageList(): Flow<PagingData<ImageEntity>>
}