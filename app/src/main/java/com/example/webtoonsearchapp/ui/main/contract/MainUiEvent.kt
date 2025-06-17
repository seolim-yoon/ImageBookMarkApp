package com.example.webtoonsearchapp.ui.main.contract

import com.example.webtoonsearchapp.base.UiEvent
import com.example.webtoonsearchapp.model.ImageUiModel

sealed interface MainUiEvent: UiEvent {
    data object Refresh: MainUiEvent
    data class ClickBookMark(
        val imageUiModel: ImageUiModel
    ): MainUiEvent
    data class ClickImageItem(
        val imageUiModel: ImageUiModel
    ): MainUiEvent
}