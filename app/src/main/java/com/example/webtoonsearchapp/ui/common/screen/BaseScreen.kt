package com.example.webtoonsearchapp.ui.common.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.webtoonsearchapp.R
import com.example.webtoonsearchapp.ui.common.graph.BottomTabNavGraph
import com.example.webtoonsearchapp.ui.common.item.BottomNavigationItem
import com.example.webtoonsearchapp.ui.common.item.TopAppBarItem
import com.example.webtoonsearchapp.util.AppState
import com.example.webtoonsearchapp.util.rememberAppState

@Composable
internal fun BaseScreen(
    baseAppState: AppState
) {
    val mainAppState: AppState = rememberAppState()

    Scaffold(
        topBar = {
            TopAppBarItem(
                isBackNav = false,
                isVisibleSearchBtn = true,
                topBarTitle = stringResource(R.string.main),
                onClickBackNav = mainAppState::navigateUp,
                onClickSearchBtn = baseAppState::navigateToSearchScreen
            )
        },
        bottomBar = {
            BottomNavigationItem(
                navigateToRoute = mainAppState::navigateToBottomBarRoute
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        BottomTabNavGraph(
            mainAppState = mainAppState,
            navigateToViewer = baseAppState::navigateToViewerScreen,
            modifier = Modifier.padding(innerPadding)
        )
    }
}