package com.example.webtoonsearchapp.util

import kotlinx.serialization.Serializable

sealed interface ScreenType {
    @Serializable
    data object Start : ScreenType
    @Serializable
    data object Main : ScreenType
    @Serializable
    data object BookMark : ScreenType
    @Serializable
    data object Search : ScreenType

    @Serializable
    data class Viewer(
        val id: Int
    ) : ScreenType
}