package com.evangelidis.t_tnews.databases

import android.annotation.SuppressLint
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ArticleRepository(private val articleDao: ArticleDao) : IArticleRepository {

    @SuppressLint("CheckResult")
    override fun insert(article: SavedArticle) {
        articleDao.saveArticle(article)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { debugLog { "Insert Success" } },
                { debugLog { "Insert Error" } }
            )
    }

    @SuppressLint("CheckResult")
    override fun delete(url: String) {
        articleDao.deleteArticle(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { debugLog { "Insert Success" } },
                { debugLog { "Insert Error" } }
            )
    }

    override fun getAll(): Observable<MutableList<SavedArticle>> = articleDao.getSavedArticles()
}

inline fun debugLog(throwable: Throwable? = null, message: () -> String) =
    Timber.d(throwable, message())
