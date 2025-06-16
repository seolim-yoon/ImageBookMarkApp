package com.example.data.mapper

import com.example.data.datasource.local.database.BookMark
import com.example.data.dto.ImageDTO
import com.example.domain.entity.ImageEntity
import javax.inject.Inject

class EntityMapper @Inject constructor() {
    fun mapToImageEntity(imageList: List<ImageDTO.ImageItem>): List<ImageEntity> =
        imageList.map { image ->
            ImageEntity(
                id = image.title + "_" + image.thumbnail,
                title = image.title,
                thumbnail = image.thumbnail,
                isBookMark = false
            )
        }


    fun mapToImageEntity(image: ImageDTO.ImageItem): ImageEntity =
        ImageEntity(
            id = image.title + "_" + image.thumbnail,
            title = image.title,
            thumbnail = image.thumbnail,
            isBookMark = false
        )

    fun mapToImageEntity(imageList: List<BookMark>): List<ImageEntity> =
        imageList.map { image ->
            ImageEntity(
                id = image.title + "_" + image.thumbnail,
                title = image.title,
                thumbnail = image.thumbnail,
                isBookMark = true
            )
        }

    fun mapToBookMark(image: ImageEntity): BookMark =
        BookMark(
            id = image.title + "_" + image.thumbnail,
            title = image.title,
            thumbnail = image.thumbnail
        )

    fun mapToImageEntity(bookMark: BookMark): ImageEntity =
        ImageEntity(
            id = bookMark.title + "_" + bookMark.thumbnail,
            title = bookMark.title,
            thumbnail = bookMark.thumbnail,
            isBookMark = true
        )
}