package com.example.data.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.data.data.local.database.AppDatabase
import com.example.data.data.local.database.entity.WebToonImage
import com.example.data.data.remote.api.ImageApi
import com.example.data.mapper.mapToWebToonImageList

@OptIn(ExperimentalPagingApi::class)
class ImageRemoteMediator(
    private val imageApi: ImageApi,
    private val database: AppDatabase
) : RemoteMediator<Int, WebToonImage>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, WebToonImage>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull()
                    ?: return MediatorResult.Success(endOfPaginationReached = true)
                lastItem.page + 1
            }
        }

        return try {
            val response = imageApi.getImageList(
                display = state.config.pageSize,
                start = DEFAULT_PAGE + ((page - 1) * state.config.pageSize)
            ).mapToWebToonImageList(page)

            with(database) {
                withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        webToonImageDao().deleteAll()
                    }
                    webToonImageDao().insertAll(response)
                }
            }

            MediatorResult.Success(endOfPaginationReached = response.isEmpty())
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    companion object {
        const val PAGE_SIZE = 50
        const val IMAGE_URL_PAGE_SIZE = 30
        const val PRE_FETCH_SIZE = 3
        const val DEFAULT_PAGE = 1
    }
}