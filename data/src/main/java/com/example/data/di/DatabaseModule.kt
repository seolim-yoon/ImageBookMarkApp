package com.example.data.di

import android.content.Context
import com.example.data.data.local.database.AppDatabase
import com.example.data.data.local.database.dao.BookMarkDao
import com.example.data.data.local.database.dao.WebToonImageDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ) = AppDatabase.getInstance(context)

    @Singleton
    @Provides
    fun provideBookMarkDao(appDatabase: AppDatabase): BookMarkDao = appDatabase.bookMarkDao()

    @Singleton
    @Provides
    fun provideWebToonImageDao(appDatabase: AppDatabase): WebToonImageDao = appDatabase.webToonImageDao()
}