package com.example.webtoonsearchapp.ui.common.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.webtoonsearchapp.ui.bookmark.BookMarkViewModel
import com.example.webtoonsearchapp.ui.bookmark.screen.BookMarkScreen
import com.example.webtoonsearchapp.ui.main.MainViewModel
import com.example.webtoonsearchapp.ui.main.screen.MainScreen
import com.example.webtoonsearchapp.util.AppState
import com.example.webtoonsearchapp.util.ScreenType

@Composable
internal fun BottomTabNavGraph(
    mainAppState: AppState,
    navigateToViewer: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = mainAppState.navController,
        startDestination = ScreenType.Main,
        modifier = modifier
    ) {
        composable<ScreenType.Main> {
            val viewModel: MainViewModel = hiltViewModel()
            val pagingList = viewModel.pagingFlow.collectAsLazyPagingItems()
            val state by viewModel.state.collectAsStateWithLifecycle()

            MainScreen(
                pagingList = pagingList,
                state = state,
                onEvent = viewModel::onEvent,
                effectFlow = viewModel.effect,
                navigateToViewer = navigateToViewer
            )
        }

        composable<ScreenType.BookMark> {
            val viewModel: BookMarkViewModel = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()

            BookMarkScreen(
                state = state,
                onEvent = viewModel::onEvent,
                effectFlow = viewModel.effect,
                navigateToViewer = navigateToViewer
            )
        }
    }
}