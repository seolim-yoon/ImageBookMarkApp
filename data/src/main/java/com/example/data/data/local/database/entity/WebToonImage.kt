package com.example.data.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WebToonImage(
    @PrimaryKey
    @ColumnInfo(name = "image_id")
    val id: String,
    val title: String,
    val link: String,
    val thumbnail: String,
    val page: Int
)