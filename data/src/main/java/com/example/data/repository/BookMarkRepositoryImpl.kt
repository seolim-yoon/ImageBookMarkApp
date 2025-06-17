package com.example.data.repository

import com.example.data.data.local.database.dao.BookMarkDao
import com.example.data.mapper.EntityMapper
import com.example.domain.entity.ImageEntity
import com.example.domain.repository.BookMarkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookMarkRepositoryImpl @Inject constructor(
    private val bookMarkDao: BookMarkDao,
    private val entityMapper: EntityMapper
) : BookMarkRepository {
    override fun getAllBookMarkItem(): Flow<List<ImageEntity>> =
        bookMarkDao.getAllBookMarkItem().map {
            entityMapper.mapToImageEntityList(it)
        }

    override suspend fun addBookMarkItem(item: ImageEntity): Long =
        bookMarkDao.addBookMarkItem(entityMapper.mapToBookMark(item))

    override suspend fun removeBookMarkItems(items: List<ImageEntity>): Int =
        bookMarkDao.removeBookMarkItems(entityMapper.mapToBookMarkList(items))
}