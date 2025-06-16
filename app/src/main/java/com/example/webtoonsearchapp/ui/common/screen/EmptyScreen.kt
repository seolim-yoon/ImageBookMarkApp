package com.example.webtoonsearchapp.ui.common.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.webtoonsearchapp.R
import com.example.webtoonsearchapp.ui.theme.WebToonSearchAppTheme

@Composable
internal fun EmptyScreen() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = null,
            modifier = Modifier.size(100.dp)
                .padding(10.dp)
        )

        Text(
            text = stringResource(R.string.empty_msg)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewEmptyScreen() {
    WebToonSearchAppTheme {
        EmptyScreen()
    }
}