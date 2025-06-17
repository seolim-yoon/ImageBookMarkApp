package com.example.webtoonsearchapp.ui.search.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.webtoonsearchapp.model.ImageUiModel
import com.example.webtoonsearchapp.ui.bookmark.item.BookMarkImageItem
import com.example.webtoonsearchapp.util.HORIZONTAL_IMAGE_ITEM_TYPE

@Composable
internal fun SearchImageListItem(
    listState: LazyListState,
    pagingList: LazyPagingItems<ImageUiModel>,
    keyword: String,
    onClickImageItem: (ImageUiModel) -> Unit
) {
    LazyColumn(
        state = listState,
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            contentType = { HORIZONTAL_IMAGE_ITEM_TYPE },
            count = pagingList.itemCount
        ) { index ->
            pagingList[index]?.let { image ->
                BookMarkImageItem(
                    image = image,
                    onClickImageItem = { onClickImageItem(image) },
                    keyword = keyword
                )
            }
        }
    }
}