package com.example.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ImageDTO(
    val display: Int = 0,
    val items: List<ImageItem> = listOf(),
    val lastBuildDate: String = "",
    val start: Int = 0,
    val total: Int = 0
) {
    @Serializable
    data class ImageItem(
        val link: String = "",
        val sizeheight: String = "",
        val sizewidth: String = "",
        val thumbnail: String = "",
        val title: String = ""
    )
}