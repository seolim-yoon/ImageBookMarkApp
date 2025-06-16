package com.example.webtoonsearchapp.mapper

import com.example.domain.entity.ImageEntity
import com.example.webtoonsearchapp.model.ImageUiModel
import javax.inject.Inject

class ImageUiMapper @Inject constructor() {
    fun mapToImageUiModel(image: ImageEntity): ImageUiModel =
        ImageUiModel(
            id = image.id,
            title = image.title,
            thumbnail = image.thumbnail,
            isBookMark = image.isBookMark,
        )

    fun mapToImageEntity(image: ImageUiModel): ImageEntity =
        ImageEntity(
            id = image.id,
            title = image.title,
            thumbnail = image.thumbnail,
            isBookMark = image.isBookMark,
        )
}