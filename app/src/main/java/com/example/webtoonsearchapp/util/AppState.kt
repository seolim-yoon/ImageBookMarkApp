package com.example.webtoonsearchapp.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController()
) = remember(navController) { AppState(navController) }

@Stable
class AppState(
    val navController: NavHostController
) {
    private val startDestinationId: Int
        get() = navController.graph.startDestinationId

    fun navigateUp() {
        navController.navigateUp()
    }

    fun navigateToBottomBarRoute(route: ScreenType) {
        navController.navigate(route) {
            launchSingleTop = true
            restoreState = true
            popUpTo(startDestinationId) {
                saveState = true
            }
        }
    }

    fun navigateToSearchScreen() {
        navController.navigate(ScreenType.Search)
    }

    fun navigateToViewerScreen(id: String) {
        navController.navigate(ScreenType.Viewer(
            id = id
        ))
    }
}