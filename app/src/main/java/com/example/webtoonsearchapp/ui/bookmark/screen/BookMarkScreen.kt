package com.example.webtoonsearchapp.ui.bookmark.screen

import androidx.compose.runtime.Composable
import com.example.webtoonsearchapp.ui.bookmark.item.BookMarkImageListItem
import com.example.webtoonsearchapp.ui.main.contract.MainUiEffect
import com.example.webtoonsearchapp.ui.main.contract.MainUiEvent
import com.example.webtoonsearchapp.ui.main.contract.MainUiState
import kotlinx.coroutines.flow.Flow

@Composable
internal fun BookMarkScreen(
    state: MainUiState,
    onEvent: (MainUiEvent) -> Unit,
    effectFlow: Flow<MainUiEffect>
) {
    BookMarkImageListItem(
        imageList = state.bookMarkList,
        isSelectionMode = state.isSelectionMode,
        selectedList = state.selectedList,
        onClickImageItem = { onEvent(MainUiEvent.ClickImageItem(it)) },
        onClickBookMark = { onEvent(MainUiEvent.ClickBookMark(it)) },
        onLongClickList = { onEvent(MainUiEvent.LongClickList) },
        onClickSave = { onEvent(MainUiEvent.SaveBookMark) },
        onClickCancel = {onEvent(MainUiEvent.CancelBookMark) }
    )
}