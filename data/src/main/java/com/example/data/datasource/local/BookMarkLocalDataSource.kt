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

    suspend fun addBookMarkItem(item: BookMark): Long = bookMarkDao.addBookMarkItem(item)

    suspend fun removeBookMarkItem(item: BookMark): Int = bookMarkDao.removeBookMarkItem(item)
}