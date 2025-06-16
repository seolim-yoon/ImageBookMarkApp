package com.example.data.datasource.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.dto.ImageDTO

class SearchImagePagingSource(
    private val requestApi: suspend (nextPage: Int) -> List<ImageDTO.ImageItem>
) : PagingSource<Int, ImageDTO.ImageItem>() {
    override fun getRefreshKey(state: PagingState<Int, ImageDTO.ImageItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageDTO.ImageItem> {
        return try {
            val pageIndex = params.key ?: DEFAULT_PAGE
            val remoteData = requestApi(pageIndex)
            val nextKey = if (remoteData.isEmpty()) null else pageIndex + 1

            LoadResult.Page(
                data = remoteData,
                prevKey = if (pageIndex == 1) null else pageIndex - 1,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        const val PAGE_SIZE = 50
        const val PRE_FETCH_SIZE = 3
        const val DEFAULT_PAGE = 1
    }
}