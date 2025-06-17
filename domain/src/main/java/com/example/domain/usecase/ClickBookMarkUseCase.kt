package com.example.domain.usecase

import com.example.domain.entity.ImageEntity
import com.example.domain.repository.BookMarkRepository
import javax.inject.Inject

class ClickBookMarkUseCase @Inject constructor(
    private val bookMarkRepository: BookMarkRepository
) {
    suspend operator fun invoke(
        isAdd: Boolean,
        bookMarkItem: ImageEntity
    ): Boolean = if (isAdd) {
        bookMarkRepository.addBookMarkItem(bookMarkItem) != -1L
    } else {
        bookMarkRepository.removeBookMarkItems(listOf(bookMarkItem)) > 0 // TODO : 여러개 삭제 처리
    }
}