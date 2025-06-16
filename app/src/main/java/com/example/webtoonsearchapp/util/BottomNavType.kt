package com.example.webtoonsearchapp.util

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.webtoonsearchapp.R

enum class BottomNavType(
    val route: ScreenType,
    @StringRes val bottomTitleRes: Int,
    val icon: ImageVector
) {
    MAIN(
        route = ScreenType.Main,
        bottomTitleRes = R.string.main,
        icon = Icons.Default.Home
    ),

    BOOKMARK(
        route = ScreenType.BookMark,
        bottomTitleRes = R.string.bookmark,
        icon = Icons.Default.Favorite
    );

    companion object {
        fun getBottomNavType(route: ScreenType): BottomNavType? =
            entries.find { route == it.route }
    }
}