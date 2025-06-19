package com.example.domain.usecase

import com.example.domain.entity.ImageEntity
import com.example.domain.repository.BookMarkRepository
import javax.inject.Inject

class ClickBookMarkUseCase @Inject constructor(
    private val bookMarkRepository: BookMarkRepository
) {
    suspend operator fun invoke(
        isAdd: Boolean,
        bookMarkItems: List<ImageEntity>
    ) {
        if (bookMarkItems.isEmpty()) return

        val ids = bookMarkItems.map { it.id }
        if (isAdd) {
            bookMarkRepository.addBookMarkItems(ids = ids)
        } else {
            bookMarkRepository.removeBookMarkItems(ids = ids)
        }
    }
}