package com.evangelidis.t_tnews.databases

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_articles")
data class SavedArticle(
    @PrimaryKey @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "source") val source: String?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "publishedDate") val publishedDate: String?,
    @ColumnInfo(name = "image") val image: String?
)
