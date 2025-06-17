package com.example.webtoonsearchapp.ui.main.contract

import com.example.webtoonsearchapp.base.UiEffect

sealed interface MainUiEffect: UiEffect {
    data object NavigateToViewer: MainUiEffect
}