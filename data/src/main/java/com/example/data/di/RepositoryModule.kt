package com.example.data.di

import com.example.data.repository.BookMarkRepositoryImpl
import com.example.data.repository.ImageRepositoryImpl
import com.example.data.repository.SearchRepositoryImpl
import com.example.domain.repository.BookMarkRepository
import com.example.domain.repository.ImageRepository
import com.example.domain.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindsImageRepository(imageRepositoryImpl: ImageRepositoryImpl): ImageRepository

    @Binds
    @Singleton
    fun bindsBookMarkRepository(bookMarkRepositoryImpl: BookMarkRepositoryImpl): BookMarkRepository

    @Binds
    @Singleton
    fun bindsSearchRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository

}