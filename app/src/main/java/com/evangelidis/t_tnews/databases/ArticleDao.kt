package com.evangelidis.t_tnews.databases

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveArticle(article: SavedArticle): Completable

    @Query("SELECT * FROM saved_articles")
    fun getSavedArticles(): Observable<MutableList<SavedArticle>>

    @Query("DELETE FROM saved_articles WHERE `url` = :url")
    fun deleteArticle(url: String): Completable
}
