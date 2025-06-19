package com.example.webtoonsearchapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import com.example.webtoonsearchapp.ui.common.graph.NavHostGraph
import com.example.webtoonsearchapp.ui.theme.WebToonSearchAppTheme
import com.example.webtoonsearchapp.util.rememberAppState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WebToonSearchAppTheme {
                val appState = rememberAppState()
                val containerColor = MaterialTheme.colorScheme.background

                Surface(
                    color = containerColor,
                    contentColor = contentColorFor(containerColor)
                ) {
                    NavHostGraph(
                        appState = appState
                    )
                }
            }
        }
    }
}
