package com.example.domain.usecase

import androidx.paging.PagingData
import com.example.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetImageUrlListUseCase @Inject constructor(
    private val imageRepository: ImageRepository
) {
    operator fun invoke(): Flow<PagingData<String>> = imageRepository.getImageUrlList()
}