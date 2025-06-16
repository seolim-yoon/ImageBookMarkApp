package com.example.webtoonsearchapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.webtoonsearchapp.ui.common.item.BottomNavigationItem
import com.example.webtoonsearchapp.ui.common.item.TopAppBarItem
import com.example.webtoonsearchapp.ui.common.screen.NavHostScreen
import com.example.webtoonsearchapp.ui.theme.WebToonSearchAppTheme
import com.example.webtoonsearchapp.util.rememberAppState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WebToonSearchAppTheme {
                val appState = rememberAppState()

                Scaffold(
                    topBar = {
                        TopAppBarItem(
                            isBackNav = false,
                            isVisibleSearchBtn = true,
                            topBarTitle = stringResource(R.string.main),
                            onClickBackNav = appState::navigateUp,
                            onClickSearchBtn = appState::navigateToSearchScreen
                        )
                    },
                    bottomBar = {
                        BottomNavigationItem(
                            navigateToRoute = appState::navigateToBottomBarRoute
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    NavHostScreen(
                        navController = appState.navController,
                        navigateToViewer = appState::navigateToViewerScreen,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
