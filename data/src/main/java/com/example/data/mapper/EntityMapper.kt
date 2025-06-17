package com.example.data.mapper

import com.example.data.data.local.database.entity.BookMark
import com.example.data.data.local.database.entity.WebToonImage
import com.example.data.dto.ImageDTO
import com.example.domain.entity.ImageEntity
import javax.inject.Inject

class EntityMapper @Inject constructor() {
    fun mapToImageEntity(image: WebToonImage): ImageEntity =
        ImageEntity(
            id = image.title + "_" + image.thumbnail,
            title = image.title,
            thumbnail = image.thumbnail,
            isBookMark = false
        )

    fun mapToImageEntityList(imageList: List<BookMark>): List<ImageEntity> =
        imageList.map { image ->
            ImageEntity(
                id = image.title + "_" + image.thumbnail,
                title = image.title,
                thumbnail = image.thumbnail,
                isBookMark = true
            )
        }

    fun mapToBookMarkList(imageList: List<ImageEntity>): List<BookMark> =
        imageList.map { image ->
            mapToBookMark(image)
        }

    fun mapToBookMark(image: ImageEntity): BookMark =
        BookMark(
            id = image.title + "_" + image.thumbnail,
            title = image.title,
            thumbnail = image.thumbnail
        )
}