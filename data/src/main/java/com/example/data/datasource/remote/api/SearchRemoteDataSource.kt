package com.example.data.datasource.remote.api

import com.example.data.dto.ImageDTO
import javax.inject.Inject

class SearchRemoteDataSource @Inject constructor(
    private val searchApi: SearchApi
) {
    suspend fun searchImage(
        keyword: String,
        display: Int,
        start: Int
    ): ImageDTO = searchApi.searchImage(
        keyword = keyword,
        display = display,
        start = start
    )
}