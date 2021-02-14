package com.evangelidis.t_tnews.models.repos

import com.evangelidis.t_tnews.interfaces.NewsContract
import com.evangelidis.t_tnews.models.api.ApiModule
import com.evangelidis.t_tnews.models.api.NewsApi
import com.evangelidis.t_tnews.models.modelsClasses.NewsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository : NewsContract.Model {

    private var apiClient: NewsApi? = null

    init {
        apiClient = ApiModule.client.create(NewsApi::class.java)
    }

    override fun loadNews(listener: NewsContract.APIListener) {
        val call = apiClient?.getNews("gr", "6e17bc511c8544c3a38989166e6a566c", 100)

        call?.enqueue(object : Callback<NewsModel> {
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                if (response.isSuccessful) {
                    response.body()?.articles?.let {
                        listener.onSuccess(it)
                    }
                } else {
                    response.body()?.let {
                        listener.onError(it)
                    }
                }
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                listener.onFailure(t)
            }
        })
    }
}
