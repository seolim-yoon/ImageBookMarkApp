package com.example.webtoonsearchapp.ui.bookmark.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.webtoonsearchapp.model.ImageUiModel
import com.example.webtoonsearchapp.ui.common.item.EditMenuItem
import com.example.webtoonsearchapp.ui.theme.WebToonSearchAppTheme
import com.example.webtoonsearchapp.util.HORIZONTAL_IMAGE_ITEM_TYPE
import com.example.webtoonsearchapp.util.PreviewImageList

@Composable
internal fun BookMarkImageListItem(
    imageList: List<ImageUiModel>,
    isSelectionMode: Boolean,
    selectedList: Set<ImageUiModel>,
    onClickImageItem: (ImageUiModel) -> Unit,
    onClickBookMark: (ImageUiModel) -> Unit,
    onLongClickList: () -> Unit,
    onClickSave: () -> Unit,
    onClickCancel: () -> Unit
) {
    Column {
        if (isSelectionMode) {
            EditMenuItem(
                isAdd = false,
                onClickSave = onClickSave,
                onClickCancel = onClickCancel
            )
        }
        LazyColumn(
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(
                key = { it.id },
                contentType = { HORIZONTAL_IMAGE_ITEM_TYPE },
                items = imageList
            ) { image ->
                BookMarkImageItem(
                    image = image,
                    isSelectionMode = isSelectionMode,
                    isChecked = selectedList.contains(image),
                    onClickImageItem = { onClickImageItem(image) },
                    onClickBookMark = { onClickBookMark(image) },
                    onLongClickList = onLongClickList
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewBookMarkImageListItem() {
    WebToonSearchAppTheme {
        BookMarkImageListItem(
            imageList = PreviewImageList,
            isSelectionMode = true,
            selectedList = emptySet(),
            onClickImageItem = {},
            onClickBookMark = {},
            onLongClickList = {},
            onClickSave = {},
            onClickCancel = {}
        )
    }
}