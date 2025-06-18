package com.example.webtoonsearchapp.ui.common.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.webtoonsearchapp.R
import com.example.webtoonsearchapp.ui.theme.WebToonSearchAppTheme

@Composable
internal fun EditMenuItem(
    isAdd: Boolean,
    onClickSave: () -> Unit,
    onClickCancel: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Spacer(
            modifier = Modifier.weight(1f)
        )
        Text(
            text = stringResource(if (isAdd) R.string.add_book_mark else R.string.remove_book_mark),
            modifier = Modifier.clickable {
                onClickSave()
            }
        )
        VerticalDivider(
            modifier = Modifier
                .height(30.dp)
                .padding(horizontal = 10.dp)
        )
        Text(
            text = stringResource(R.string.cancel),
            modifier = Modifier.clickable {
                onClickCancel()
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewEditMenuItem() {
    WebToonSearchAppTheme {
        EditMenuItem(
            isAdd = true,
            onClickSave = {},
            onClickCancel = {}
        )
    }
}