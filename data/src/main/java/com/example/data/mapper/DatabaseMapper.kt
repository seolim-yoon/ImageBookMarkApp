package com.example.data.mapper

import com.example.data.data.local.database.entity.WebToonImage
import com.example.data.dto.ImageDTO

fun ImageDTO.mapToWebToonImageList(page: Int): List<WebToonImage> =
    items.map { image ->
        WebToonImage(
            id = image.title + "_" + image.thumbnail,
            title = image.title,
            thumbnail = image.thumbnail,
            page = page
        )
    }
