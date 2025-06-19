package com.example.webtoonsearchapp.ui.search.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.webtoonsearchapp.model.ImageUiModel
import com.example.webtoonsearchapp.ui.common.screen.EmptyScreen
import com.example.webtoonsearchapp.ui.search.contract.SearchUiEffect
import com.example.webtoonsearchapp.ui.search.contract.SearchUiEvent
import com.example.webtoonsearchapp.ui.search.contract.SearchUiState
import com.example.webtoonsearchapp.ui.search.item.SearchBarItem
import com.example.webtoonsearchapp.ui.search.item.SearchResultItem
import com.example.webtoonsearchapp.util.DEFAULT_KEYWORD
import kotlinx.coroutines.flow.Flow

@Composable
internal fun SearchScreen(
    pagingList: LazyPagingItems<ImageUiModel>,
    state: SearchUiState,
    onEvent: (SearchUiEvent) -> Unit,
    effectFlow: Flow<SearchUiEffect>,
    navigateToViewer: (String) -> Unit
) {
    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        effectFlow.collect { effect ->
            when (effect) {
                is SearchUiEffect.ScrollToTop -> {
                    listState.scrollToItem(0)
                }
                is SearchUiEffect.NavigateToViewer -> {
                    navigateToViewer(effect.id)
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(10.dp)
    ) {
        SearchBarItem(
            inputText = state.inputKeyword,
            onValueChange = { keyword -> onEvent(SearchUiEvent.InputKeyword(keyword)) },
            onClickClearBtn = { onEvent(SearchUiEvent.InputKeyword(DEFAULT_KEYWORD)) }
        )

        if (state.inputKeyword.isNotBlank()) {
            if (pagingList.itemCount == 0) {
                EmptyScreen()
            } else {
                SearchResultItem(
                    listState = listState,
                    pagingList = pagingList,
                    keyword = state.inputKeyword,
                    onClickImageItem = { onEvent(SearchUiEvent.ClickImageItem(it)) },
                    onRefresh = { onEvent(SearchUiEvent.Refresh) }
                )
            }
        }
    }
}