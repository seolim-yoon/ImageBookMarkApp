package com.example.domain.usecase

import androidx.paging.PagingData
import com.example.domain.entity.ImageEntity
import com.example.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMainImageListUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    operator fun invoke(keyword: String): Flow<PagingData<ImageEntity>> = searchRepository.searchImage(keyword)
}