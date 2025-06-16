package com.example.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.example.domain.entity.ImageEntity
import com.example.domain.repository.BookMarkRepository
import com.example.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMainImageListUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
    private val bookMarkRepository: BookMarkRepository
) {
    operator fun invoke(
        keyword: String
    ): Flow<PagingData<ImageEntity>> = flow {
        val bookMarkIds = bookMarkRepository.getAllBookMarkItemIds()

        emitAll(
            searchRepository.searchImage(keyword).map { pagingData ->
                pagingData.map { image ->
                    image.copy(
                        isBookMark = bookMarkIds.contains(image.id)
                    )
                }
            }
        )
    }
}