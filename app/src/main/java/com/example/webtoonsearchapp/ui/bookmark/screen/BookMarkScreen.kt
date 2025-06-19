package com.example.webtoonsearchapp.ui.bookmark.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.webtoonsearchapp.ui.bookmark.contract.BookMarkUiEffect
import com.example.webtoonsearchapp.ui.bookmark.contract.BookMarkUiEvent
import com.example.webtoonsearchapp.ui.bookmark.contract.BookMarkUiState
import com.example.webtoonsearchapp.ui.bookmark.item.BookMarkImageListItem
import kotlinx.coroutines.flow.Flow

@Composable
internal fun BookMarkScreen(
    state: BookMarkUiState,
    onEvent: (BookMarkUiEvent) -> Unit,
    effectFlow: Flow<BookMarkUiEffect>,
    navigateToViewer: (String) -> Unit
) {
    LaunchedEffect(Unit) {
        effectFlow.collect { effect ->
            when (effect) {
                is BookMarkUiEffect.NavigateToViewer -> {
                    navigateToViewer(effect.url)
                }
            }
        }
    }

    BookMarkImageListItem(
        imageList = state.bookMarkList,
        isSelectionMode = state.isSelectionMode,
        selectedList = state.selectedList,
        onClickImageItem = { if (!state.isSelectionMode) onEvent(BookMarkUiEvent.ClickImageItem(it)) },
        onClickBookMark = { onEvent(BookMarkUiEvent.ClickBookMark(it)) },
        onLongClickList = { onEvent(BookMarkUiEvent.LongClickList) },
        onClickSave = { onEvent(BookMarkUiEvent.SaveBookMark) },
        onClickCancel = {onEvent(BookMarkUiEvent.CancelBookMark) }
    )
}