package com.example.webtoonsearchapp.util

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.webtoonsearchapp.model.ImageUiModel

class ImageItemParameterProvider(
    override val values: Sequence<ImageUiModel> = sequenceOf(
        defaultCase,
        longTitleCase,
        bookMarkCase
    )
): PreviewParameterProvider<ImageUiModel>

private val defaultCase = ImageUiModel(
    id = "",
    title = "타이틀입니다",
    thumbnail = "",
    isBookMark = false
)

private val longTitleCase = ImageUiModel(
    id = "",
    title = "타이틀입니다 타이틀입니다 타이틀입니다 타이틀입니다 타이틀입니다 타이틀입니다",
    thumbnail = "",
    isBookMark = false
)

private val bookMarkCase = ImageUiModel(
    id = "",
    title = "타이틀입니다 타이틀입니다 타이틀입니다 타이틀입니다 타이틀입니다 타이틀입니다",
    thumbnail = "",
    isBookMark = true
)

val PreviewImageList = List(5) { index ->
    ImageUiModel(
        id = index.toString(),
        title = "타이틀입니다",
        thumbnail = "",
        isBookMark = false
    )
}