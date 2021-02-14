package com.evangelidis.t_tnews.interfaces

import com.evangelidis.t_tnews.models.modelsClasses.Article
import com.evangelidis.t_tnews.models.modelsClasses.NewsModel

interface NewsContract {

    interface Model {
        fun loadNews(listener: APIListener)
    }

    interface View : BaseContract.View {
        fun setRecyclerView(articles: MutableList<Article>)
        fun displayErrorMessage()
    }

    interface Presenter {
        fun loadNews()
    }

    interface APIListener {
        fun onSuccess(newsList: MutableList<Article>)
        fun onError(response: NewsModel)
        fun onFailure(t: Throwable?)
    }
}
