package com.example.data.repository

import com.example.data.data.local.database.dao.BookMarkDao
import com.example.data.mapper.EntityMapper
import com.example.domain.entity.ImageEntity
import com.example.domain.repository.BookMarkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BookMarkRepositoryImpl @Inject constructor(
    private val bookMarkDao: BookMarkDao,
    private val entityMapper: EntityMapper
) : BookMarkRepository {
    override fun getAllBookMarkItem(): Flow<List<ImageEntity>> =
        bookMarkDao.getAllBookMarkItem().map {
            entityMapper.mapToImageEntityList(it)
        }

    override suspend fun replaceBookmarkItems(items: List<ImageEntity>) {
        withContext(Dispatchers.IO) {
            bookMarkDao.removeAllBookMarkItems()
            bookMarkDao.addBookMarkItems(entityMapper.mapToBookMarkList(items))
        }
    } // TODO : 예외
}