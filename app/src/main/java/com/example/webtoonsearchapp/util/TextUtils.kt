package com.example.webtoonsearchapp.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

fun buildHighlightString(
    fullText: String,
    keyword: String,
    highlightColor: Color = Color.Red
): AnnotatedString {
    if (keyword.isBlank()) return AnnotatedString(fullText)

    val regex = Regex(keyword)
    var lastIndex = 0

    return buildAnnotatedString {
        regex.findAll(fullText).forEach { matchResult ->
            val start = matchResult.range.first
            val end = matchResult.range.last + 1

            append(fullText.substring(lastIndex, start))

            withStyle(style = SpanStyle(color = highlightColor)) {
                append(fullText.substring(start, end))
            }

            lastIndex = end
        }

        if (lastIndex < fullText.length) {
            append(fullText.substring(lastIndex))
        }
    }
}
