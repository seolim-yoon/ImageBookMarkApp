package com.example.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.data.datasource.remote.api.SearchApi
import com.example.data.mapper.EntityMapper
import com.example.data.datasource.remote.paging.SearchImagePagingSource
import com.example.domain.entity.ImageEntity
import com.example.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchApi: SearchApi,
    private val entityMapper: EntityMapper
) : SearchRepository {
    override fun searchImage(
        keyword: String
    ): Flow<PagingData<ImageEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = SearchImagePagingSource.PAGE_SIZE,
                prefetchDistance = SearchImagePagingSource.PRE_FETCH_SIZE
            ),
            pagingSourceFactory = {
                SearchImagePagingSource(requestApi = { pageIndex ->
                    searchApi.searchImage(
                        keyword = keyword,
                        display = SearchImagePagingSource.PAGE_SIZE,
                        start = SearchImagePagingSource.DEFAULT_PAGE + ((pageIndex - 1) * SearchImagePagingSource.PAGE_SIZE)
                    ).items
                })
            }
        ).flow.map { pagingData -> pagingData.map { image -> entityMapper.mapToImageEntity(image) } }
    }
}