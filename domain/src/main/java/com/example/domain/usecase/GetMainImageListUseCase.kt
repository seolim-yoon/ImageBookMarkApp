package com.example.domain.usecase

import androidx.paging.PagingData
import com.example.domain.entity.ImageEntity
import com.example.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMainImageListUseCase @Inject constructor(
    private val imageRepository: ImageRepository
) {
    operator fun invoke(): Flow<PagingData<ImageEntity>> = imageRepository.getImageList()
}