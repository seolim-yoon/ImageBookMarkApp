package com.example.webtoonsearchapp.ui.main.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.webtoonsearchapp.model.ImageUiModel
import com.example.webtoonsearchapp.util.IMAGE_GRID
import com.example.webtoonsearchapp.util.VERTICAL_IMAGE_ITEM_TYPE

@Composable
internal fun MainImageListItem(
    pagingList: LazyPagingItems<ImageUiModel>,
    onClickImageItem: (ImageUiModel) -> Unit,
    onClickBookMark: (ImageUiModel) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(IMAGE_GRID),
        contentPadding = PaddingValues(20.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            contentType = { VERTICAL_IMAGE_ITEM_TYPE },
            count = pagingList.itemCount
        ) { index ->
            pagingList[index]?.let { image ->
                MainImageItem(
                    image = image,
                    onClickImageItem = { onClickImageItem(image) },
                    onClickBookMark = { onClickBookMark(image) }
                )
            }
        }
    }
}
