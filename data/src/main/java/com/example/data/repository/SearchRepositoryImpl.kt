package com.example.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.data.data.local.database.dao.WebToonImageDao
import com.example.data.data.paging.ImageRemoteMediator
import com.example.data.mapper.EntityMapper
import com.example.domain.entity.ImageEntity
import com.example.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val webToonImageDao: WebToonImageDao,
    private val entityMapper: EntityMapper
) : SearchRepository {
    override fun getWebToonItemByKeyword(keyword: String): Flow<PagingData<ImageEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = ImageRemoteMediator.PAGE_SIZE,
                prefetchDistance = ImageRemoteMediator.PRE_FETCH_SIZE
            ),
            pagingSourceFactory = {
                webToonImageDao.getWebToonItemByKeyword(keyword)
            }
        ).flow.map { pagingData -> pagingData.map { image -> entityMapper.mapToImageEntity(image) } }
    }
}