package com.example.webtoonsearchapp.ui.common.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.webtoonsearchapp.ui.common.screen.BaseScreen
import com.example.webtoonsearchapp.ui.search.SearchViewModel
import com.example.webtoonsearchapp.ui.search.screen.SearchScreen
import com.example.webtoonsearchapp.ui.viewer.ViewerViewModel
import com.example.webtoonsearchapp.ui.viewer.screen.ViewerScreen
import com.example.webtoonsearchapp.util.AppState
import com.example.webtoonsearchapp.util.ScreenType

@Composable
internal fun NavHostGraph(
    appState: AppState
) {
    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = ScreenType.Base
    ) {
        composable<ScreenType.Base> {
            BaseScreen(
                baseAppState = appState
            )
        }

        composable<ScreenType.Search> {
            val viewModel: SearchViewModel = hiltViewModel()
            val pagingList = viewModel.pagingFlow.collectAsLazyPagingItems()
            val state by viewModel.state.collectAsStateWithLifecycle()

            SearchScreen(
                pagingList = pagingList,
                state = state,
                onEvent = viewModel::onEvent,
                effectFlow = viewModel.effect,
                navigateToViewer = appState::navigateToViewerScreen
            )
        }

        composable<ScreenType.Viewer> { backStackEntry ->
            val viewModel: ViewerViewModel = hiltViewModel()
            val pagingList = viewModel.pagingFlow.collectAsLazyPagingItems()
            val linkUrl = backStackEntry.arguments?.getString("url") ?: ""

            ViewerScreen(
                pagingList = pagingList,
                currentLinkUrl = linkUrl
            )
        }
    }
}