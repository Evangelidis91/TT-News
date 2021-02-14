package com.evangelidis.t_tnews.views.savedArticles

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evangelidis.t_tnews.databases.SavedArticle
import com.evangelidis.t_tnews.databinding.ItemArticleBinding
import com.evangelidis.t_tnews.views.main.ArticleItemCallback

class SavedNewsAdapter(var callback: ArticleItemCallback) : RecyclerView.Adapter<SavedArticlesViewHolder>() {

    var articles = mutableListOf<SavedArticle>()
        set(value) {
            articles.clear()
            articles.addAll(value.reversed())
            notifyDataSetChanged()
        }

    var savedArticles = mutableListOf<SavedArticle>()
        set(value) {
            savedArticles.clear()
            savedArticles.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedArticlesViewHolder =
        SavedArticlesViewHolder(
            ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: SavedArticlesViewHolder, position: Int) =
        holder.bind(articles[position], true, callback)

    override fun getItemCount() = articles.count()

//    private fun findIfInWishList(position: Int): Boolean {
//        return savedArticles.find { it.url == articles[position].url } != null
//    }
}
