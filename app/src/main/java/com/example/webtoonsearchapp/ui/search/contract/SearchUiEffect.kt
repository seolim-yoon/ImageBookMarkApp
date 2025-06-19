package com.example.webtoonsearchapp.ui.search.contract

import com.example.webtoonsearchapp.base.UiEffect

sealed interface SearchUiEffect: UiEffect {
    data object ScrollToTop: SearchUiEffect
    data class NavigateToViewer(val url: String): SearchUiEffect
}