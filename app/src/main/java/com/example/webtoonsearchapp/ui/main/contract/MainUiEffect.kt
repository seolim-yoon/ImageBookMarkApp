package com.example.webtoonsearchapp.ui.main.contract

import com.example.webtoonsearchapp.base.UiEffect

sealed interface MainUiEffect: UiEffect {
    data class NavigateToViewer(val id: String): MainUiEffect
}