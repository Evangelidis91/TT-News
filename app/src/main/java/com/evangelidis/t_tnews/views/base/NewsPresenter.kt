package com.evangelidis.t_tnews.views.base

import com.evangelidis.t_tnews.interfaces.NewsContract
import com.evangelidis.t_tnews.models.modelsClasses.Article
import com.evangelidis.t_tnews.models.modelsClasses.NewsModel
import com.evangelidis.t_tnews.models.repos.NewsRepository

class NewsPresenter : BasePresenter<NewsContract.View>(), NewsContract.Presenter, NewsContract.APIListener {

    override fun loadNews() {
        view?.setToolbarTitle("Τα νέα σήμερα")
        view?.showLoader()
        NewsRepository().loadNews(this)
    }

    override fun onSuccess(newsList: MutableList<Article>) {
        view?.setRecyclerView(newsList)
        view?.hideLoader()
    }

    override fun onError(response: NewsModel) {
        view?.displayErrorMessage()
    }

    override fun onFailure(t: Throwable?) {
        view?.displayErrorMessage()
    }
}
