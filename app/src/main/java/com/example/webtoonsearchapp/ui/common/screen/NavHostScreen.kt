package com.example.webtoonsearchapp.ui.common.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.webtoonsearchapp.ui.bookmark.screen.BookMarkScreen
import com.example.webtoonsearchapp.ui.main.MainViewModel
import com.example.webtoonsearchapp.ui.main.screen.MainScreen
import com.example.webtoonsearchapp.ui.search.screen.SearchScreen
import com.example.webtoonsearchapp.ui.viewer.ViewerScreen
import com.example.webtoonsearchapp.util.ScreenType

@Composable
internal fun NavHostScreen(
    navController: NavHostController,
    navigateToViewer:() -> Unit,
    modifier: Modifier = Modifier
) {

    val mainViewModel: MainViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = ScreenType.Main,
        modifier = modifier
    ) {
        composable<ScreenType.Main> {
            val pagingList = mainViewModel.pagingFlow.collectAsLazyPagingItems()

            MainScreen(
                pagingList = pagingList,
                onEvent = mainViewModel::onEvent,
                effectFlow = mainViewModel.effect
            )
        }

        composable<ScreenType.BookMark> {
            val state by mainViewModel.state.collectAsStateWithLifecycle()

            BookMarkScreen(
                state = state,
                onEvent = mainViewModel::onEvent,
                effectFlow = mainViewModel.effect
            )
        }

        composable<ScreenType.Search> {
            SearchScreen()
        }

        composable<ScreenType.Viewer> {
            ViewerScreen()
        }
    }
}