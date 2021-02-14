package com.evangelidis.t_tnews.views.main

import com.evangelidis.t_tnews.databases.SavedArticle

interface ArticleItemCallback {
    fun navigateToUrl(url: String?)
    fun saveArticle(article: SavedArticle)
    fun removeSavedArticle(url: String)
}
