package com.evangelidis.t_tnews.databases

import io.reactivex.Observable

interface IArticleRepository {

    fun insert(article: SavedArticle)

    fun delete(url: String)

    fun getAll(): Observable<MutableList<SavedArticle>>
}
