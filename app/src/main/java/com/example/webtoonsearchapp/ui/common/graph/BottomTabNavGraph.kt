package com.example.webtoonsearchapp.ui.common.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.webtoonsearchapp.ui.bookmark.screen.BookMarkScreen
import com.example.webtoonsearchapp.ui.main.MainViewModel
import com.example.webtoonsearchapp.ui.main.contract.MainUiEffect
import com.example.webtoonsearchapp.ui.main.screen.MainScreen
import com.example.webtoonsearchapp.util.AppState
import com.example.webtoonsearchapp.util.ScreenType

@Composable
internal fun BottomTabNavGraph(
    mainAppState: AppState,
    navigateToViewer: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val mainViewModel: MainViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        mainViewModel.effect.collect { effect ->
            when (effect) {
                is MainUiEffect.NavigateToViewer -> {
                    navigateToViewer(effect.id)
                }
            }
        }
    }

    NavHost(
        navController = mainAppState.navController,
        startDestination = ScreenType.Main,
        modifier = modifier
    ) {
        composable<ScreenType.Main> {
            val state by mainViewModel.state.collectAsStateWithLifecycle()
            val pagingList = mainViewModel.pagingFlow.collectAsLazyPagingItems()

            MainScreen(
                pagingList = pagingList,
                state = state,
                onEvent = mainViewModel::onEvent
            )
        }

        composable<ScreenType.BookMark> {
            val state by mainViewModel.state.collectAsStateWithLifecycle()

            BookMarkScreen(
                state = state,
                onEvent = mainViewModel::onEvent
            )
        }
    }
}