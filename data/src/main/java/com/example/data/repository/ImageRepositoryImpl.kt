package com.example.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.data.data.local.database.AppDatabase
import com.example.data.data.paging.ImageRemoteMediator
import com.example.data.data.remote.api.ImageApi
import com.example.data.mapper.EntityMapper
import com.example.domain.entity.ImageEntity
import com.example.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val imageApi: ImageApi,
    private val database: AppDatabase,
    private val entityMapper: EntityMapper
) : ImageRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getImageList(): Flow<PagingData<ImageEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = ImageRemoteMediator.PAGE_SIZE,
                prefetchDistance = ImageRemoteMediator.PRE_FETCH_SIZE
            ),
            remoteMediator = ImageRemoteMediator(
                imageApi = imageApi,
                database = database
            ),
            pagingSourceFactory = {
                database.webToonImageDao().getAllWebToonItem()
            }
        ).flow.map { pagingData -> pagingData.map { image -> entityMapper.mapToImageEntity(image) } }
    }
}