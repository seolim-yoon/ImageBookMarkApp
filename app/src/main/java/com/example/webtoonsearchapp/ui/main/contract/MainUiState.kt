package com.example.webtoonsearchapp.ui.main.contract

import com.example.webtoonsearchapp.base.UiState
import com.example.webtoonsearchapp.model.ImageUiModel

data class MainUiState(
    val isSelectionMode: Boolean = false,
    val selectedList: Set<ImageUiModel> = emptySet()
): UiState
