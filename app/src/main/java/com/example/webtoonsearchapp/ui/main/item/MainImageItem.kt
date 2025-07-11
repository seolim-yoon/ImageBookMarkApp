package com.example.webtoonsearchapp.ui.main.item

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.webtoonsearchapp.model.ImageUiModel
import com.example.webtoonsearchapp.ui.common.item.CommonGlideImage
import com.example.webtoonsearchapp.ui.theme.WebToonSearchAppTheme
import com.example.webtoonsearchapp.util.ImageItemParameterProvider

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun MainImageItem(
    image: ImageUiModel,
    isSelectionMode: Boolean,
    isChecked: Boolean,
    onClickImageItem: () -> Unit,
    onClickBookMark: () -> Unit,
    onLongClickList: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.combinedClickable(
            onClick = onClickImageItem,
            onLongClick = onLongClickList
        )
    ) {
        Box {
            CommonGlideImage(
                thumbnailUrl = image.thumbnail,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(3f / 4f)
                    .clip(
                        shape = RoundedCornerShape(5.dp)
                    )
            )

            if (isSelectionMode) {
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { onClickBookMark() },
                    modifier = Modifier.align(Alignment.TopEnd)
                )
            } else {
                if (image.isBookMark) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        tint = Color.Red,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(4.dp)
                    )
                }
            }
        }

        Text(
            text = image.title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMainImageItem(@PreviewParameter(ImageItemParameterProvider::class) image: ImageUiModel) {
    WebToonSearchAppTheme {
        MainImageItem(
            image = image,
            isSelectionMode = true,
            isChecked = image.isBookMark,
            onClickImageItem = {},
            onClickBookMark = {},
            onLongClickList = {}
        )
    }
}
