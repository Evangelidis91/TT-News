package com.evangelidis.t_tnews.models.modelsClasses

import com.google.gson.annotations.SerializedName

data class NewsModel(
    @SerializedName("articles") val articles: MutableList<Article>?,
    @SerializedName("status") val status: String?,
    @SerializedName("totalResults") val totalResults: Int?
)

data class Article(
    @SerializedName("author") val author: Any?,
    @SerializedName("content") val content: Any?,
    @SerializedName("description") val description: Any?,
    @SerializedName("publishedAt") val publishedAt: String?,
    @SerializedName("source") val source: Source?,
    @SerializedName("title") val title: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("urlToImage") val urlToImage: String?
)

data class Source(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?
)
