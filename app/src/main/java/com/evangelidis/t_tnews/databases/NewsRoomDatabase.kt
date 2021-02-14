package com.evangelidis.t_tnews.databases

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SavedArticle::class], version = 1)
abstract class NewsRoomDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao
}
