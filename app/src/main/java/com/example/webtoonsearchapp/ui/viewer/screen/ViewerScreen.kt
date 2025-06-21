package com.example.webtoonsearchapp.ui.viewer.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.paging.compose.LazyPagingItems
import com.bumptech.glide.Glide
import com.bumptech.glide.MemoryCategory
import com.example.webtoonsearchapp.ui.viewer.item.GlideImageItem
import com.example.webtoonsearchapp.util.IMAGE_URL_ITEM_TYPE
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

@Composable
internal fun ViewerScreen(
    pagingList: LazyPagingItems<String>,
    currentLinkUrl: String
) {
    val listState = rememberLazyListState()
    val context = LocalContext.current

    LaunchedEffect(currentLinkUrl) {
        snapshotFlow { pagingList.itemSnapshotList.items }
            .map { it.indexOfFirst { it == currentLinkUrl } }
            .filter { it != -1 }
            .firstOrNull()
            ?.let { index ->
                listState.scrollToItem(index)
            }
    }

    DisposableEffect(Unit) {
        Glide.get(context).setMemoryCategory(MemoryCategory.HIGH)

        onDispose {
            Glide.get(context).setMemoryCategory(MemoryCategory.NORMAL)
        }
    }

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            key = { index -> pagingList[index] ?: index },
            contentType = { IMAGE_URL_ITEM_TYPE },
            count = pagingList.itemCount
        ) { index ->

            pagingList[index]?.let { imageUrl ->
                GlideImageItem(imageUrl = imageUrl)
            }
        }
    }
}