package com.example.webtoonsearchapp.ui.viewer.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.example.webtoonsearchapp.ui.viewer.item.GlideImageItem
import com.example.webtoonsearchapp.util.IMAGE_URL_ITEM_TYPE

@Composable
internal fun ViewerScreen(
    pagingList: LazyPagingItems<String>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            contentType = { IMAGE_URL_ITEM_TYPE },
            count = pagingList.itemCount
        ) { index ->
            pagingList[index]?.let { imageUrl ->
               GlideImageItem(
                   imageUrl = imageUrl
               )
            }
        }
    }
}