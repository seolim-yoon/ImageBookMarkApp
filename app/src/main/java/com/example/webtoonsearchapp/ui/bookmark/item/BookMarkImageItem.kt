package com.example.webtoonsearchapp.ui.bookmark.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.webtoonsearchapp.util.DEFAULT_KEYWORD
import com.example.webtoonsearchapp.util.ImageItemParameterProvider
import com.example.webtoonsearchapp.util.buildHighlightString

@Composable
internal fun BookMarkImageItem(
    image: ImageUiModel,
    onClickImageItem: () -> Unit,
    keyword: String = ""
) {
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClickImageItem()
            }
    ) {
        CommonGlideImage(
            thumbnailUrl = image.thumbnail,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(100.dp)
                .aspectRatio(3f / 4f)
                .clip(
                    shape = RoundedCornerShape(5.dp)
                )
        )

        val annotatedText = buildHighlightString(
            fullText = image.title,
            keyword = keyword
        )

        Text(
            text = annotatedText,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(top = 5.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewBookMarkImageItem(@PreviewParameter(ImageItemParameterProvider::class) image: ImageUiModel) {
    WebToonSearchAppTheme {
        BookMarkImageItem(
            image = image,
            keyword = DEFAULT_KEYWORD,
            onClickImageItem = {}
        )
    }
}
