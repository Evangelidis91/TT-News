package com.evangelidis.t_tnews.databases

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ArticlePresenter(private val iArticleInteractor: IArticleInteractor) : IArticlePresenter, ViewModel() {

    private val allSavedArticles: MutableLiveData<MutableList<SavedArticle>> = MutableLiveData()

    init {
        loadSavedArticles()
    }

    override fun insert(article: SavedArticle) {
        iArticleInteractor.insert(article)
    }

    override fun delete(url: String) {
        iArticleInteractor.delete(url)
    }

    override fun getAll(): LiveData<MutableList<SavedArticle>> = allSavedArticles

    @SuppressLint("CheckResult")
    override fun loadSavedArticles() {
        iArticleInteractor.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { articles -> allSavedArticles.postValue(articles) },
                { debugLog { "Error getting info from interactor into presenter" } }
            )
    }
}
