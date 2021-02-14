package com.evangelidis.t_tnews.models.api

import com.evangelidis.t_tnews.models.modelsClasses.NewsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    fun getNews(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String,
        @Query("pageSize") pageSize: Int
    ): Call<NewsModel>
}
