package com.evangelidis.t_tnews.views.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evangelidis.t_tnews.databases.SavedArticle
import com.evangelidis.t_tnews.databinding.ItemArticleBinding
import com.evangelidis.t_tnews.models.modelsClasses.Article

class NewsAdapter(var callback: ArticleItemCallback) : RecyclerView.Adapter<ArticleViewHolder>() {

    var articles = mutableListOf<Article>()
        set(value) {
            articles.clear()
            articles.addAll(value)
            notifyDataSetChanged()
        }

    var savedArticles = mutableListOf<SavedArticle>()
        set(value) {
            savedArticles.clear()
            savedArticles.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder =
        ArticleViewHolder(
            ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) =
        holder.bind(articles[position], findIfInWishList(position), callback)

    override fun getItemCount() = articles.count()

    private fun findIfInWishList(position: Int): Boolean {
        return savedArticles.find { it.url == articles[position].url } != null
    }
}
