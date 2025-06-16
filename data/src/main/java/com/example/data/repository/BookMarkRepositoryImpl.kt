package com.example.data.repository

import com.example.data.datasource.local.BookMarkLocalDataSource
import com.example.data.mapper.EntityMapper
import com.example.domain.entity.ImageEntity
import com.example.domain.repository.BookMarkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookMarkRepositoryImpl @Inject constructor(
    private val bookMarkLocalDataSource: BookMarkLocalDataSource,
    private val entityMapper: EntityMapper
) : BookMarkRepository {
    override fun getAllBookMarkItem(): Flow<List<ImageEntity>> =
        bookMarkLocalDataSource.getAllBookMarkItem().map {
            entityMapper.mapToImageEntity(it)
        }

    override suspend fun getAllBookMarkItemIds(): List<String> =
        bookMarkLocalDataSource.getAllBookMarkItemIds()

    override suspend fun addBookMarkItem(item: ImageEntity) =
        bookMarkLocalDataSource.addBookMarkItem(entityMapper.mapToBookMark(item))

    override suspend fun removeBookMarkItem(item: ImageEntity) =
        bookMarkLocalDataSource.removeBookMarkItem(entityMapper.mapToBookMark(item))
}