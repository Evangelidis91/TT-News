package com.evangelidis.t_tnews.databases

import androidx.lifecycle.LiveData

interface IArticlePresenter {

    fun insert(article: SavedArticle)

    fun delete(url: String)

    fun getAll(): LiveData<MutableList<SavedArticle>>

    fun loadSavedArticles()
}
