package com.example.webtoonsearchapp.ui.common.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.webtoonsearchapp.ui.theme.WebToonSearchAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopAppBarItem(
    isBackNav: Boolean = false,
    isVisibleSearchBtn: Boolean = false,
    topBarTitle: String = "",
    onClickBackNav: () -> Unit = {},
    onClickSearchBtn: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                text = topBarTitle,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            if (isBackNav) {
                IconButton(
                    onClick = onClickBackNav
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        },
        actions = {
            if (isVisibleSearchBtn) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            onClickSearchBtn()
                        }
                        .padding(end = 16.dp)
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewTopAppBarItem() {
    WebToonSearchAppTheme {
        TopAppBarItem(
            isBackNav = true,
            isVisibleSearchBtn = true,
            topBarTitle = "TopBar",
            onClickBackNav = {},
            onClickSearchBtn = {}
        )
    }
}