package com.example.webtoonsearchapp.ui.bookmark.contract

import com.example.webtoonsearchapp.base.UiState
import com.example.webtoonsearchapp.model.ImageUiModel

data class BookMarkUiState(
    val isSelectionMode: Boolean = false,
    val selectedList: Set<ImageUiModel> = emptySet(),
    val bookMarkList: List<ImageUiModel> = emptyList()
): UiState
