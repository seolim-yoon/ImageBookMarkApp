package com.example.webtoonsearchapp.ui.search.contract

import com.example.webtoonsearchapp.base.LoadState
import com.example.webtoonsearchapp.base.UiState
import com.example.webtoonsearchapp.util.DEFAULT_KEYWORD

data class SearchUiState(
    val loadState: LoadState = LoadState.Success,
    val inputKeyword: String = DEFAULT_KEYWORD
): UiState
