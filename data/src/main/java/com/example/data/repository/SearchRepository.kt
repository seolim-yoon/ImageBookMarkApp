package com.example.data.repository

import com.example.data.datasource.remote.api.SearchRemoteDataSource
import com.example.data.dto.ImageDTO
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val searchRemoteDataSource: SearchRemoteDataSource
){
    suspend fun searchImage(
        keyword: String,
        display: Int,
        start: Int
    ): ImageDTO = searchRemoteDataSource.searchImage(
        keyword = keyword,
        display = display,
        start = start
    )
}