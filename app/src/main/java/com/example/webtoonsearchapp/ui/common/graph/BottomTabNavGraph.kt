package com.example.webtoonsearchapp.ui.common.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.webtoonsearchapp.ui.bookmark.screen.BookMarkScreen
import com.example.webtoonsearchapp.ui.main.MainViewModel
import com.example.webtoonsearchapp.ui.main.contract.MainUiEvent
import com.example.webtoonsearchapp.ui.main.screen.MainScreen
import com.example.webtoonsearchapp.util.ScreenType

@Composable
internal fun BottomTabNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val mainViewModel: MainViewModel = hiltViewModel()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    LaunchedEffect(navBackStackEntry?.destination?.route) {
        mainViewModel.onEvent(MainUiEvent.OnTabSelected)
    }
    NavHost(
        navController = navController,
        startDestination = ScreenType.Main,
        modifier = modifier
    ) {
        composable<ScreenType.Main> {
            val state by mainViewModel.state.collectAsStateWithLifecycle()
            val pagingList = mainViewModel.pagingFlow.collectAsLazyPagingItems()

            MainScreen(
                pagingList = pagingList,
                state = state,
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
    }
}