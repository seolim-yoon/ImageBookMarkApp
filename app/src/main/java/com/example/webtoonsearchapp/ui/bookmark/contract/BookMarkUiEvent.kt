package com.example.webtoonsearchapp.ui.bookmark.contract

import com.example.webtoonsearchapp.base.UiEvent
import com.example.webtoonsearchapp.model.ImageUiModel

sealed interface BookMarkUiEvent: UiEvent {
    data object LongClickList: BookMarkUiEvent
    data class ClickBookMark(
        val imageUiModel: ImageUiModel
    ): BookMarkUiEvent
    data object SaveBookMark: BookMarkUiEvent
    data object CancelBookMark: BookMarkUiEvent
    data class ClickImageItem(
        val imageUiModel: ImageUiModel
    ): BookMarkUiEvent
}