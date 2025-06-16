package com.example.data.datasource.remote.api

import com.example.data.dto.ImageDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("search/image")
    suspend fun searchImage(
        @Query("query") keyword: String,
        @Query("display") display: Int,
        @Query("start") start: Int
    ): ImageDTO
}