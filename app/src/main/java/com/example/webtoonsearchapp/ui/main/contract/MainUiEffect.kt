package com.example.webtoonsearchapp.ui.main.contract

import com.example.webtoonsearchapp.base.UiEffect

sealed interface MainUiEffect: UiEffect {
    data object ScrollToTop: MainUiEffect
    data object NavigateToSearch: MainUiEffect
    data object NavigateToViewer: MainUiEffect
}