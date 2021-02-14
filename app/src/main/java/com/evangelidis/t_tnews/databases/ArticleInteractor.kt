package com.evangelidis.t_tnews.databases

import io.reactivex.Observable

class ArticleInteractor(private val iArticleRepository: IArticleRepository) : IArticleInteractor {

    override fun insert(article: SavedArticle) {
        iArticleRepository.insert(article)
    }

    override fun delete(url: String) {
        iArticleRepository.delete(url)
    }

    override fun getAll(): Observable<MutableList<SavedArticle>> = iArticleRepository.getAll()
}
