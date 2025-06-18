package com.example.domain.usecase

import com.example.domain.entity.ImageEntity
import com.example.domain.repository.BookMarkRepository
import javax.inject.Inject

class ClickBookMarkUseCase @Inject constructor(
    private val bookMarkRepository: BookMarkRepository
) {
    suspend operator fun invoke(
        bookMarkItems: List<ImageEntity>
    ) = bookMarkRepository.replaceBookmarkItems(bookMarkItems)
}