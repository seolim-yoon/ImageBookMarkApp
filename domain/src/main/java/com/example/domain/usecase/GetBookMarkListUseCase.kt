package com.example.domain.usecase

import com.example.domain.entity.ImageEntity
import com.example.domain.repository.BookMarkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookMarkListUseCase @Inject constructor(
    private val bookMarkRepository: BookMarkRepository
) {
    operator fun invoke(): Flow<List<ImageEntity>> = bookMarkRepository.getAllBookMarkItems()
}