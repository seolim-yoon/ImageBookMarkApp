package com.example.webtoonsearchapp.ui.common.item

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.webtoonsearchapp.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
internal fun CommonGlideImage(
    thumbnailUrl: String,
    contentScale: ContentScale,
    modifier: Modifier = Modifier
) {
    if (LocalInspectionMode.current) {
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = null,
            modifier = modifier
                .background(
                    color = Color.Gray
                )
        )
    } else {
        GlideImage(
            model = thumbnailUrl,
            contentDescription = null,
            contentScale = contentScale,
            modifier = modifier
        )
    }
}
