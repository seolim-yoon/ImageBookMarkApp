package com.example.data.data.remote.api

import com.example.data.dto.ImageDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApi {
    @GET("search/image")
    suspend fun getImageList(
        @Query("query") keyword: String = "만화",
        @Query("display") display: Int,
        @Query("start") start: Int
    ): ImageDTO
}