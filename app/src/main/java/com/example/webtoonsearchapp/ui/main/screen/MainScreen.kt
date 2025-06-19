package com.example.webtoonsearchapp.ui.main.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.webtoonsearchapp.model.ImageUiModel
import com.example.webtoonsearchapp.ui.common.screen.ErrorScreen
import com.example.webtoonsearchapp.ui.main.contract.MainUiEffect
import com.example.webtoonsearchapp.ui.main.contract.MainUiEvent
import com.example.webtoonsearchapp.ui.main.contract.MainUiState
import com.example.webtoonsearchapp.ui.main.item.MainImageListItem
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun MainScreen(
    pagingList: LazyPagingItems<ImageUiModel>,
    state: MainUiState,
    onEvent: (MainUiEvent) -> Unit,
    effectFlow: Flow<MainUiEffect>,
    navigateToViewer: (String) -> Unit
) {
    val loadState = pagingList.loadState.refresh
    val pullToRefreshState = rememberPullRefreshState(
        refreshing = loadState is LoadState.Loading,
        onRefresh = {
            pagingList.refresh()
        }
    )

    LaunchedEffect(Unit) {
        effectFlow.collect { effect ->
            when (effect) {
                is MainUiEffect.NavigateToViewer -> {
                    navigateToViewer(effect.id)
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(
                state = pullToRefreshState,
                enabled = loadState !is LoadState.Error
            )
    ) {
        when (loadState) {
            is LoadState.Loading -> {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth()
                )
            }

            is LoadState.NotLoading -> {
                MainImageListItem(
                    pagingList = pagingList,
                    isSelectionMode = state.isSelectionMode,
                    selectedList = state.selectedList,
                    onClickImageItem = { onEvent(MainUiEvent.ClickImageItem(it)) },
                    onClickBookMark = { onEvent(MainUiEvent.ClickBookMark(it)) },
                    onLongClickList = { onEvent(MainUiEvent.LongClickList) },
                    onClickSave = { onEvent(MainUiEvent.SaveBookMark) },
                    onClickCancel = {onEvent(MainUiEvent.CancelBookMark) }
                )
            }

            is LoadState.Error -> {
                ErrorScreen(
                    errorMessage = loadState.error.message.toString(),
                    onRefresh = {
                        pagingList.refresh()
                    }
                )
            }
        }

        PullRefreshIndicator(
            refreshing = loadState is LoadState.Loading,
            state = pullToRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}