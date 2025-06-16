package com.example.webtoonsearchapp.ui.common.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
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
    NavHost(
        navController = navController,
        startDestination = ScreenType.Main,
        modifier = modifier
    ) {
        composable<ScreenType.Main> {
            val viewModel: MainViewModel = hiltViewModel()
            val pagingList = viewModel.pagingFlow.collectAsLazyPagingItems()

            MainScreen(
                pagingList = pagingList,
                onEvent = viewModel::onEvent,
                effectFlow = viewModel.effect
            )
        }

        composable<ScreenType.BookMark> {
            BookMarkScreen()
        }

        composable<ScreenType.Search> {
            SearchScreen()
        }

        composable<ScreenType.Viewer> {
            ViewerScreen()
        }
    }
}