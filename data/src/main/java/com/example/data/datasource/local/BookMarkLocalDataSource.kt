package com.example.data.datasource.local

import com.example.data.datasource.local.database.BookMark
import com.example.data.datasource.local.database.BookMarkDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookMarkLocalDataSource @Inject constructor(
    private val bookMarkDao: BookMarkDao
) {
    fun getAllBookMarkItem(): Flow<List<BookMark>> = bookMarkDao.getAllBookMarkItem()

    suspend fun getAllBookMarkItemIds(): List<String> = bookMarkDao.getAllBookMarkItemIds()

    suspend fun addBookMarkItem(item: BookMark) = bookMarkDao.addBookMarkItem(item)

    suspend fun removeBookMarkItem(item: BookMark) = bookMarkDao.removeBookMarkItem(item)
}