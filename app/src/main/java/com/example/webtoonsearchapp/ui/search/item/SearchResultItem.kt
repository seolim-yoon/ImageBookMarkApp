package com.example.webtoonsearchapp.ui.search.item

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.webtoonsearchapp.model.ImageUiModel
import com.example.webtoonsearchapp.ui.common.screen.ErrorScreen

@Composable
internal fun SearchResultItem(
    listState: LazyListState,
    pagingList: LazyPagingItems<ImageUiModel>,
    keyword: String,
    onClickImageItem: (ImageUiModel) -> Unit,
    onRefresh: () -> Unit
) {
    when (pagingList.loadState.refresh) {
        is LoadState.Loading -> {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth()
            )
        }

        is LoadState.NotLoading -> {
            SearchImageListItem(
                listState = listState,
                pagingList = pagingList,
                keyword = keyword,
                onClickImageItem = onClickImageItem
            )
        }

        is LoadState.Error -> {
            ErrorScreen(
                errorMessage = (pagingList.loadState.refresh as LoadState.Error).error.message.toString(),
                onRefresh = onRefresh
            )
        }
    }
}