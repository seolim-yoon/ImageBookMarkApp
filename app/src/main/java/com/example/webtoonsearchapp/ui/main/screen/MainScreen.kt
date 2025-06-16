package com.example.webtoonsearchapp.ui.main.screen

import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import com.example.webtoonsearchapp.model.ImageUiModel
import com.example.webtoonsearchapp.ui.main.contract.MainUiEffect
import com.example.webtoonsearchapp.ui.main.contract.MainUiEvent
import com.example.webtoonsearchapp.ui.main.item.MainImageListItem
import kotlinx.coroutines.flow.Flow

@Composable
internal fun MainScreen(
    pagingList: LazyPagingItems<ImageUiModel>,
    onEvent: (MainUiEvent) -> Unit,
    effectFlow: Flow<MainUiEffect>
) {
    MainImageListItem(
        pagingList = pagingList,
        onClickImageItem = { onEvent(MainUiEvent.ClickImageItem(it)) },
        onClickBookMark = { onEvent(MainUiEvent.ClickBookMark(it)) }
    )
}