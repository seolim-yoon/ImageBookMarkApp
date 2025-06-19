package com.example.webtoonsearchapp.ui.search.contract

import com.example.webtoonsearchapp.base.UiEvent
import com.example.webtoonsearchapp.model.ImageUiModel

sealed interface SearchUiEvent: UiEvent {
    data class InputKeyword(
        val keyword: String
    ): SearchUiEvent
    data class ClickImageItem(
        val imageUiModel: ImageUiModel
    ): SearchUiEvent
}