package com.example.webtoonsearchapp.ui.bookmark.contract

import com.example.webtoonsearchapp.base.UiEffect

sealed interface BookMarkUiEffect: UiEffect {
    data class NavigateToViewer(val id: String): BookMarkUiEffect
}