package com.example.data.datasource.local.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookMark(
    @PrimaryKey
    @ColumnInfo(name = "image_id")
    val id: String,
    val title: String,
    val thumbnail: String
)
