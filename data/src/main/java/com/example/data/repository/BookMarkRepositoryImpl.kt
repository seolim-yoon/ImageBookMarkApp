package com.example.data.repository

import com.example.data.data.local.database.dao.WebToonImageDao
import com.example.data.mapper.EntityMapper
import com.example.domain.entity.ImageEntity
import com.example.domain.repository.BookMarkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookMarkRepositoryImpl @Inject constructor(
    private val webToonImageDao: WebToonImageDao,
    private val entityMapper: EntityMapper
) : BookMarkRepository {
    override fun getAllBookMarkItems(): Flow<List<ImageEntity>> =
        webToonImageDao.getAllBookMarkItems().map {
            entityMapper.mapToImageEntityList(it)
        }

    override suspend fun addBookMarkItems(ids: List<String>) = webToonImageDao.addBookMarkItems(ids)
    override suspend fun removeBookMarkItems(ids: List<String>) = webToonImageDao.removeBookMarkItems(ids)
}