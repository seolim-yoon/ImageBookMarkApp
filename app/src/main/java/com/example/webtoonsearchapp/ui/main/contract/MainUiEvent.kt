package com.example.webtoonsearchapp.ui.main.contract

import com.example.webtoonsearchapp.base.UiEvent
import com.example.webtoonsearchapp.model.ImageUiModel

sealed interface MainUiEvent: UiEvent {
    data object LongClickList: MainUiEvent
    data class ClickBookMark(
        val imageUiModel: ImageUiModel
    ): MainUiEvent
    data object SaveBookMark: MainUiEvent
    data object CancelBookMark: MainUiEvent
    data class ClickImageItem(
        val imageUiModel: ImageUiModel
    ): MainUiEvent
    data object OnTabSelected: MainUiEvent
}