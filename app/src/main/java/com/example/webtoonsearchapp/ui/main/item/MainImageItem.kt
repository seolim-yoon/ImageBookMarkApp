package com.example.webtoonsearchapp.ui.main.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.webtoonsearchapp.model.ImageUiModel
import com.example.webtoonsearchapp.ui.common.item.CommonGlideImage
import com.example.webtoonsearchapp.ui.theme.WebToonSearchAppTheme
import com.example.webtoonsearchapp.util.ImageItemParameterProvider

@Composable
internal fun MainImageItem(
    image: ImageUiModel,
    onClickImageItem: () -> Unit,
    onClickBookMark: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.clickable {
            onClickImageItem()
        }
    ) {
        Box {
            CommonGlideImage(
                thumbnailUrl = image.thumbnail,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(3f / 4f)
                    .clip(
                        shape = RoundedCornerShape(10.dp)
                    )
            )

            Icon(
                imageVector = if (image.isBookMark) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .clickable {
                        onClickBookMark()
                    }
                    .padding(8.dp)
            )
        }
        Text(
            text = image.title,
            maxLines = 1,
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
            onClickImageItem = {},
            onClickBookMark = {}
        )
    }
}
