package com.example.webtoonsearchapp.util

import kotlinx.serialization.Serializable

sealed interface ScreenType {
    @Serializable
    data object Main : ScreenType
    data object BookMark : ScreenType
    data object Search : ScreenType

    @Serializable
    data class Viewer(
        val id: Int
    ) : ScreenType
}