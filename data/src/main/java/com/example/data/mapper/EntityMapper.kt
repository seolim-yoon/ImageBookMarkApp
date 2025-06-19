package com.example.data.mapper

import com.example.data.data.local.database.entity.WebToonImage
import com.example.domain.entity.ImageEntity
import javax.inject.Inject

class EntityMapper @Inject constructor() {
    fun mapToImageEntityList(imageList: List<WebToonImage>): List<ImageEntity> =
        imageList.map { image ->
            mapToImageEntity(image)
        }

    fun mapToImageEntity(image: WebToonImage): ImageEntity =
        ImageEntity(
            id = image.title + "_" + image.thumbnail,
            title = image.title,
            link = image.link,
            thumbnail = image.thumbnail,
            isBookMark = image.isBookMark
        )

    fun mapToBookMarkList(imageList: List<ImageEntity>): List<WebToonImage> =
        imageList.map { image ->
            mapToBookMark(image)
        }

    private fun mapToBookMark(image: ImageEntity): WebToonImage =
        WebToonImage(
            id = image.title + "_" + image.thumbnail,
            title = image.title,
            link = image.link,
            thumbnail = image.thumbnail,
            isBookMark = image.isBookMark

        )
}