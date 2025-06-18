package com.example.webtoonsearchapp.ui.main.screen

import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import com.example.webtoonsearchapp.model.ImageUiModel
import com.example.webtoonsearchapp.ui.main.contract.MainUiEffect
import com.example.webtoonsearchapp.ui.main.contract.MainUiEvent
import com.example.webtoonsearchapp.ui.main.contract.MainUiState
import com.example.webtoonsearchapp.ui.main.item.MainImageListItem
import kotlinx.coroutines.flow.Flow

@Composable
internal fun MainScreen(
    pagingList: LazyPagingItems<ImageUiModel>,
    state: MainUiState,
    onEvent: (MainUiEvent) -> Unit,
    effectFlow: Flow<MainUiEffect>
) {
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